package ua.com.foxminded.webapp;

import org.apache.log4j.Logger;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.GroupDaoImpl;
import ua.com.foxminded.domain.Group;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewGroupsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            GroupDaoImpl groups = new GroupDaoImpl(connectionFactory);
            List<Group> groupsList = groups.getAll();
            for (Group group : groupsList) {
                int id = group.getId();
                request.setAttribute("id", id);

                String title = group.getTitle();
                request.setAttribute("title", title);

                String description = group.getDescription();
                request.setAttribute("description", description);
            }

            request.setAttribute("groups", groupsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view_groups.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            log.error("Exception when we try display groups", e.getCause());
            throw new ServletException(e);
        }
    }
}


