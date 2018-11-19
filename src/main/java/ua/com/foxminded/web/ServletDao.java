package ua.com.foxminded.web;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.GroupDaoImpl;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.dao.TeacherDaoImpl;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.domain.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ServletDao extends HttpServlet {
    private static final Logger log = Logger.getLogger(GroupDaoImpl.class);

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
                log.error("Error in DaoServlet when request students data" + e);
                throw new ServletException(e);
            }
        } else if (person.equals("teacher")) {
            try {
                ConnectionFactory daoFaconnectionFactorytory = new ConnectionFactory();
                TeacherDaoImpl teachers = new TeacherDaoImpl(daoFaconnectionFactorytory);
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
                log.error("Error in DaoServlet when request teachers data" + e);
                throw new ServletException(e);
            }
        }
    }
}


