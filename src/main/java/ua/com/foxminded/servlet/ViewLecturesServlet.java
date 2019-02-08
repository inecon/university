package ua.com.foxminded.servlet;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.dao.LectureDao;
import ua.com.foxminded.dao.TeacherDao;
import ua.com.foxminded.domain.Lecture;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Configurable
@Log4j
public class ViewLecturesServlet extends HttpServlet {
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_LECTURES_PAGE = "/lecture.jsp";
    private static String VIEW_ALL_LECTURES_PAGE = "/view_lectures.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Inject
    LectureDao lectureDao;
    @Inject
    TeacherDao teacherDao;
    @Inject
    GroupDao groupDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("lecture", lectureDao.getAll());
            forward = VIEW_ALL_LECTURES_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_LECTURES_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_LECTURES_PAGE;
            Integer lecture_id = Integer.parseInt(request.getParameter("id"));
            Lecture lecture = lectureDao.getById(lecture_id);
            request.setAttribute("lecture", lecture);
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //if request.getPathInfo == null - adding new lecture
        if (request.getPathInfo() == null) {
            LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("date"));
            String subject = request.getParameter("subject");
            String teacher_id = request.getParameter("teacher_id");
            String group_id = request.getParameter("group_id");
            String classroom = request.getParameter("classroom");
            if (id == null || id.isEmpty()) {
                //sort list to find max id
                List<Lecture> lectureList = lectureDao.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (lectureList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = lectureList.get(lectureList.size() - 1).getId() + 1;
                }
                Lecture lecture = new Lecture(newId, localDateTime, subject, teacherDao.getById(Integer.parseInt(teacher_id)),
                        groupDao.getById(Integer.parseInt(group_id)), Integer.parseInt(classroom));
                lectureDao.create(lecture);
            } else {

                Lecture lecture = new Lecture(Integer.parseInt(id), localDateTime, subject, teacherDao.getById(Integer.parseInt(teacher_id)),
                        groupDao.getById(Integer.parseInt(group_id)), Integer.parseInt(classroom));
                lectureDao.update(lecture);
            }
            forward = VIEW_ALL_LECTURES_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_LECTURES_PAGE;
            Integer lectureId = Integer.parseInt(id);
            lectureDao.deleteById(lectureId);
            request.setAttribute("lecture", lectureDao.getAll());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("lecture", lectureDao.getAll());
        view.forward(request, response);
    }
}


