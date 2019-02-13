package ua.com.foxminded.servlet;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.domain.Student;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Configurable
@Log4j
public class ViewStudentsServlet extends HttpServlet {
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_STUDENT_PAGE = "/student.jsp";
    private static String VIEW_ALL_STUDENTS_PAGE = "/view_students.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Inject
    StudentDao studentDao;
    @Inject
    GroupDao groupDao;
    @Inject
    Student student;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("students", studentDao.getAll());
            forward = VIEW_ALL_STUDENTS_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_STUDENT_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_STUDENT_PAGE;
            Integer student_id = Integer.parseInt(request.getParameter("id"));
            Student student = studentDao.getById(student_id);
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
            student.setName(request.getParameter("name"));
            student.setSurName(request.getParameter("surName"));
            student.setGender(request.getParameter("gender"));
            student.setAge(Integer.parseInt(request.getParameter("age")));
            student.setGroup(groupDao.getById(Integer.parseInt(request.getParameter("group_id"))));
            if (id == null || id.isEmpty()) {
                List<Student> studentList = studentDao.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (studentList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = studentList.get(studentList.size() - 1).getId() + 1;
                }
                student.setId(newId);
                studentDao.create(student);
            } else {
                student.setId(Integer.parseInt(id));
                studentDao.update(student);
            }
            forward = VIEW_ALL_STUDENTS_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_STUDENTS_PAGE;
            studentDao.delete(Integer.parseInt(id));
            request.setAttribute("students", studentDao.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("students", studentDao.getAll());
        view.forward(request, response);
    }
}


