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
    ConnectionFactory connectionFactory = new ConnectionFactory();
    TeacherDaoImpl teachers = new TeacherDaoImpl(connectionFactory);
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_TEACHERS_PAGE = "/teacher.jsp";
    private static String VIEW_ALL_TEACHERS_PAGE = "/view_teachers.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("teachers", teachers.getAll());
            forward = VIEW_ALL_TEACHERS_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_TEACHERS_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_TEACHERS_PAGE;
            Integer teacher_id = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = teachers.getById(teacher_id);
            request.setAttribute("teacher", teacher);
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //if request.getPathInfo == null - adding new student
        if (request.getPathInfo() == null) {
            String name = request.getParameter("name");
            String surName = request.getParameter("surName");
            String gender = request.getParameter("gender");
            Integer age = Integer.parseInt(request.getParameter("age"));
            if (id == null || id.isEmpty()) {
                //sort list to find max id
                List<Teacher> teacherList = teachers.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (teacherList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = teacherList.get(teacherList.size() - 1).getId() + 1;
                }
                teachers.create(newId, name, surName, gender, age);
            } else {
                teachers.update(name, surName, gender, age, Integer.parseInt(id));
            }
            forward = VIEW_ALL_TEACHERS_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_TEACHERS_PAGE;
            Integer teacherId = Integer.parseInt(id);
            teachers.deleteById(teacherId);
            request.setAttribute("teachers", teachers.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("teachers", teachers.getAll());
        view.forward(request, response);
    }
}


