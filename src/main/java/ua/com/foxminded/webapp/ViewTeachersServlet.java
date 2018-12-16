package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.TeacherDaoImpl;
import ua.com.foxminded.domain.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewTeachersServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
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
            log.error("Exception when we try display teachers", e.getCause());
            throw new ServletException(e);
        }
    }
}


