package ua.com.foxminded.webapp;

import ua.com.foxminded.dao.*;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.domain.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

public class ServletDao extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String person = request.getParameter("name");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if (person.equals("student")) {
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                StudentDaoImpl students = new StudentDaoImpl(connectionFactory);
                List<Student> studentList = students.getAll();
                for (Student student : studentList) {
                    int id = student.getId();
                    request.setAttribute("id", id);

                    String name = student.getName();
                    request.setAttribute("name", name);

                    String surName = student.getSurName();
                    request.setAttribute("surName", surName);

                    String gender = student.getGender();
                    request.setAttribute("gender", gender);

                    Group group = student.getGroup();
                    request.setAttribute("group", group);

                    int age = student.getAge();
                    request.setAttribute("age", age);
                }

                request.setAttribute("students", studentList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view_students.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else if (person.equals("teacher")) {
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                TeacherDaoImpl teachers = new TeacherDaoImpl(connectionFactory);
                List<Teacher> teacherList = teachers.getAll();
                for (Teacher teacher : teacherList) {
                    int id = teacher.getId();
                    request.setAttribute("id", id);

                    String name = teacher.getName();
                    request.setAttribute("name", name);

                    String surName = teacher.getSurName();
                    request.setAttribute("surName", surName);

                    String gender = teacher.getGender();
                    request.setAttribute("gender", gender);

                    int age = teacher.getAge();
                    request.setAttribute("age", age);
                }

                request.setAttribute("teachers", teacherList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view_teachers.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        else if (person.equals("group")) {
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                GroupDaoImpl groups = new GroupDaoImpl(connectionFactory);
                List<Group> groupsList = groups.getAll();
                for (Group group : groupsList) {
                    int id = group.getId();
                    request.setAttribute("id", id);

                    String title = group.getTitle();
                    request.setAttribute("title", title);

                    String description = group.getDescription();
                    request.setAttribute("description", description);
                }

                request.setAttribute("groups", groupsList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view_groups.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        else if (person.equals("lecture")) {
            try {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                LectureDaoImpl lectures = new LectureDaoImpl(connectionFactory);
                List<Lecture> lectureList = lectures.getAll();
                for (Lecture lecture : lectureList) {
                    LocalDateTime date = lecture.getDate();
                    request.setAttribute("date", date);

                    String subject = lecture.getSubject();
                    request.setAttribute("subject", subject);

                    Teacher teacher = lecture.getTeacher();
                    request.setAttribute("teacher", teacher);

                    Group group = lecture.getGroup();
                    request.setAttribute("group", group);

                    Integer classroom = lecture.getClassroom();
                    request.setAttribute("classroom", classroom );
                }

                request.setAttribute("lectures", lectureList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view_lectures.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }
}


