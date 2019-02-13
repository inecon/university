package ua.com.foxminded.servlet;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.domain.Group;

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
public class ViewGroupsServlet extends HttpServlet {

    String forward = "";
    private static final Integer START_ID = 1;

    private static String CREATE_OR_EDIT_GROUPS_PAGE = "/group.jsp";
    private static String VIEW_ALL_GROUPS_PAGE = "/view_groups.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Inject
    GroupDao groupDao;

    @Inject
    Group group;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        if (request.getPathInfo() == null) {
            request.setAttribute("groups", groupDao.getAll());
            forward = VIEW_ALL_GROUPS_PAGE;
        } else if (request.getPathInfo().equals("/insert/")) {
            forward = CREATE_OR_EDIT_GROUPS_PAGE;
        } else if (request.getPathInfo().equals("/edit/")) {
            forward = CREATE_OR_EDIT_GROUPS_PAGE;
            Integer group_id = Integer.parseInt(request.getParameter("id"));
            Group group = groupDao.getById(group_id);
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
            group.setTitle(request.getParameter("title"));
            group.setDescription(request.getParameter("description"));
            if (id == null || id.isEmpty()) {
                //sort list to find max id
                List<Group> groupList = groupDao.getAll();
                Integer newId;
                //if no users, to first user set id = 1
                if (groupList.isEmpty()) {
                    newId = START_ID;
                } else {
                    newId = groupList.get(groupList.size() - 1).getId() + 1;
                }
                group.setId(newId);
                groupDao.create(group);
            } else {
                group.setId(Integer.parseInt(id));
                groupDao.update(group);
            }
            forward = VIEW_ALL_GROUPS_PAGE;
        } else if (request.getPathInfo().equals("/delete/")) {
            forward = VIEW_ALL_GROUPS_PAGE;
            groupDao.delete(Integer.parseInt(id));
            request.setAttribute("groups", groupDao.getAll());
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        request.setAttribute("groups", groupDao.getAll());
        view.forward(request, response);
    }
}


