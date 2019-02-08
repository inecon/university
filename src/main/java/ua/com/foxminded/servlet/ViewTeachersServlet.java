package ua.com.foxminded.servlet;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.foxminded.dao.TeacherDao;
import ua.com.foxminded.domain.Teacher;

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
public class ViewTeachersServlet extends HttpServlet {
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_TEACHERS_PAGE = "/teacher.jsp";
    private static String VIEW_ALL_TEACHERS_PAGE = "/view_teachers.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Inject
    TeacherDao teacherDao;
    @Inject
    Teacher teacher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("teachers", teacherDao.getAll());
            forward = VIEW_ALL_TEACHERS_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_TEACHERS_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_TEACHERS_PAGE;
            Integer teacher_id = Integer.parseInt(request.getParameter("id"));
            Teacher teacher = teacherDao.getById(teacher_id);
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
            teacher.setName(request.getParameter("name"));
            teacher.setSurName(request.getParameter("surName"));
            teacher.setGender(request.getParameter("gender"));
            teacher.setAge(Integer.parseInt(request.getParameter("age")));
            teacher.setSubject(request.getParameter("subject"));
            if (id == null || id.isEmpty()) {
                //sort list to find max id
                List<Teacher> teacherList = teacherDao.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (teacherList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = teacherList.get(teacherList.size() - 1).getId() + 1;
                }
                teacher.setId(newId);
                teacherDao.create(teacher);
            } else {
                teacher.setId(Integer.parseInt(id));
                teacherDao.update(teacher);
            }
            forward = VIEW_ALL_TEACHERS_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_TEACHERS_PAGE;
            Integer teacherId = Integer.parseInt(id);
            teacherDao.deleteById(teacherId);
            request.setAttribute("teachers", teacherDao.getAll());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("teachers", teacherDao.getAll());
        view.forward(request, response);
    }
}


