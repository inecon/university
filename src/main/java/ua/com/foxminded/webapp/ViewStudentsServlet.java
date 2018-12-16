package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewStudentsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
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
            log.error("Exception when we try display students", e.getCause());
            throw new ServletException(e);
        }
    }
}


