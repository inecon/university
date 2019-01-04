package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.*;
import ua.com.foxminded.domain.Lecture;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ViewLecturesServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
    ConnectionFactory connectionFactory = new ConnectionFactory();
    LectureDaoImpl lectures = new LectureDaoImpl(connectionFactory);
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_LECTURES_PAGE = "/lecture.jsp";
    private static String VIEW_ALL_LECTURES_PAGE = "/view_lectures.jsp";

    GroupDao groupDao = new GroupDaoImpl(connectionFactory);
    TeacherDao teacherDao = new TeacherDaoImpl(connectionFactory);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("lectures", lectures.getAll());
            forward = VIEW_ALL_LECTURES_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_LECTURES_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_LECTURES_PAGE;
            Integer lecture_id = Integer.parseInt(request.getParameter("id"));
            Lecture lecture = lectures.getById(lecture_id);
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
            String date = request.getParameter("date");
            String subject = request.getParameter("subject");
            String teacher_id = request.getParameter("teacher_id");
            String group_id = request.getParameter("group_id");
            String classroom = request.getParameter("classroom");
            if (id == null || id.isEmpty()) {
                //sort list to find max id
                List<Lecture> lectureList = lectures.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (lectureList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = lectureList.get(lectureList.size() - 1).getId() + 1;
                }
                Lecture lecture = new Lecture(newId, LocalDateTime.parse(date), subject, teacherDao.getById(Integer.parseInt(teacher_id)),
                        groupDao.getById(Integer.parseInt(group_id)), Integer.parseInt(classroom));
                lectures.create(lecture);
            } else {
                Lecture lecture = new Lecture(Integer.parseInt(id), LocalDateTime.parse(date), subject, teacherDao.getById(Integer.parseInt(teacher_id)),
                        groupDao.getById(Integer.parseInt(group_id)), Integer.parseInt(classroom));
                lectures.update(lecture);
            }
            forward = VIEW_ALL_LECTURES_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_LECTURES_PAGE;
            Integer lectureId = Integer.parseInt(id);
            lectures.deleteById(lectureId);
            request.setAttribute("lectures", lectures.getAll());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("lectures", lectures.getAll());
        view.forward(request, response);
    }
}


