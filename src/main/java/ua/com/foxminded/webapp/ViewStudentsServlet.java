package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.StudentDaoImpl;
import ua.com.foxminded.domain.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class ViewStudentsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

     ConnectionFactory connectionFactory;

     @Autowired
     @Qualifier("connectionFactory")
     public void setConnectionFactory(ConnectionFactory connectionFactory) {
         this.connectionFactory = connectionFactory;
     }

    //StudentDaoImpl students;
    StudentDaoImpl students = new StudentDaoImpl(connectionFactory);
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_STUDENT_PAGE = "/student.jsp";
    private static String VIEW_ALL_STUDENTS_PAGE = "/view_students.jsp";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("students", students.getAll());
            forward = VIEW_ALL_STUDENTS_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_STUDENT_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_STUDENT_PAGE;
            Integer student_id = Integer.parseInt(request.getParameter("id"));
            Student student = students.getById(student_id);
            request.setAttribute("student", student);
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
                List<Student> studentList = students.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (studentList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = studentList.get(studentList.size() - 1).getId() + 1;
                }
                students.create(newId, name, surName, gender, age);
            } else {
                students.update(name, surName, gender, age, Integer.parseInt(id));
            }
            forward = VIEW_ALL_STUDENTS_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_STUDENTS_PAGE;
            Integer studentId = Integer.parseInt(id);
            students.deleteById(studentId);
            request.setAttribute("students", students.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("students", students.getAll());
        view.forward(request, response);
    }
}


