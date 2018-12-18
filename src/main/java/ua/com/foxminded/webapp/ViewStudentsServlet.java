package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ViewStudentsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
    ConnectionFactory connectionFactory = new ConnectionFactory();
    StudentDaoImpl students = new StudentDaoImpl(connectionFactory);

    private static String INSERT_OR_EDIT = "/student.jsp";
    private static String LIST_STUDENTS = "/view_students.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String forward = "";
        String action = request.getParameter("action");
        //action uses for delete or update students if we fist time on page it is null - just print list of students
        if (action == null) {
            request.setAttribute("students", students.getAll());
            forward = LIST_STUDENTS;
        } else if (action.equalsIgnoreCase("delete")) {
            Integer id = Integer.parseInt(request.getParameter("student_id"));
            students.deleteById(id);
            forward = LIST_STUDENTS;
            request.setAttribute("students", students.getAll());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            Integer student_id = Integer.parseInt(request.getParameter("student_id"));
            Student student = students.getById(student_id);
            request.setAttribute("student", student);
        } else {
            //this works when adding new student
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surName = request.getParameter("surName");
        String gender = request.getParameter("gender");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String id = request.getParameter("id");
        Comparator<Student> byId = Comparator.comparing(Student::getId);
        if (id == null || id.isEmpty()) {
            //sort list to find max id
            List<Student> studentList = students.getAll();
            studentList.sort(byId);
            Integer newId;
            //if no users, to first user set id = 1
            if (studentList.isEmpty()) {
                newId = 1;
            } else {
                newId = studentList.get(studentList.size() - 1).getId() + 1;
            }
            students.create(newId, name, surName, gender, age);//i think about this during refactoring
        } else {
            students.update(name, surName, gender, age, Integer.parseInt(id));
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_STUDENTS);
        List<Student> sortedStudentList = students.getAll();
        sortedStudentList.sort(byId);
        request.setAttribute("students", sortedStudentList);
        view.forward(request, response);
    }
}


