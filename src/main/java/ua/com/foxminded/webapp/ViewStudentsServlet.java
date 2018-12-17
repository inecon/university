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

public class ViewStudentsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
    ConnectionFactory connectionFactory = new ConnectionFactory();
    StudentDaoImpl students = new StudentDaoImpl(connectionFactory);

    private static String INSERT_OR_EDIT = "/student.jsp";
    private static String LIST_STUDENTS = "/view_students.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            students.deleteById(id);
            forward = LIST_STUDENTS;
            request.setAttribute("students", students.getAll());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int student_id = Integer.parseInt(request.getParameter("student_id"));
            Student student = students.getById(student_id);
            request.setAttribute("student", student);
        } else if (action.equalsIgnoreCase("students")){
            forward = LIST_STUDENTS;
            request.setAttribute("students", students.getAll());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        String name = request.getParameter("name");
        String surName = request.getParameter("surName");
        String gender = request.getParameter("gender");
        Integer age = Integer.parseInt(request.getParameter("age"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        if(id == null || false/*id.isEmpty()*/)//move this after refactor
        {
            students.create(id,name,surName,gender,age);
        }
        else
        {
            students.update(name,surName,gender,age,id);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_STUDENTS);
        request.setAttribute("students", students.getAll());
        view.forward(request, response);
    }
}


