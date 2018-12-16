package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.LectureDaoImpl;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Teacher;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            LectureDaoImpl lectures = new LectureDaoImpl(connectionFactory);
            List<Lecture> lectureList = lectures.getAll();
            for (Lecture lecture : lectureList) {
                LocalDateTime date = lecture.getDate();
                request.setAttribute("date", date);

                String subject = lecture.getSubject();
                request.setAttribute("subject", subject);

                Teacher teacher = lecture.getTeacher();
                request.setAttribute("teacher", teacher);

                Group group = lecture.getGroup();
                request.setAttribute("group", group);

                Integer classroom = lecture.getClassroom();
                request.setAttribute("classroom", classroom);
            }

            request.setAttribute("lectures", lectureList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view_lectures.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            log.error("Exception when we try display lectures", e.getCause());
            throw new ServletException(e);
        }
    }
}


