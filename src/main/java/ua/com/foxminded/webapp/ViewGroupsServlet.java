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
    ConnectionFactory connectionFactory = new ConnectionFactory();
    GroupDaoImpl groups = new GroupDaoImpl(connectionFactory);
    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_GROUPS_PAGE = "/group.jsp";
    private static String VIEW_ALL_GROUPS_PAGE = "/view_groups.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("groups", groups.getAll());
            forward = VIEW_ALL_GROUPS_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_GROUPS_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_GROUPS_PAGE;
            Integer group_id = Integer.parseInt(request.getParameter("id"));
            Group group = groups.getById(group_id);
            request.setAttribute("group", group);
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //if request.getPathInfo == null - adding new student
        if (request.getPathInfo() == null) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            if (id == null || id.isEmpty()) {
                //sort list to find max id
                List<Group> groupList = groups.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (groupList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = groupList.get(groupList.size() - 1).getId() + 1;
                }
                groups.create(newId, title, description);
            } else {
                groups.update(title, description, Integer.parseInt(id));
            }
            forward = VIEW_ALL_GROUPS_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_GROUPS_PAGE;
            Integer groupId = Integer.parseInt(id);
            groups.deleteById(groupId);
            request.setAttribute("groups", groups.getAll());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("groups", groups.getAll());
        view.forward(request, response);
    }
}


