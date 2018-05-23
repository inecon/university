package ua.com.foxminded.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.domain.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupDaoImplTest {
    String VALID_TITLE = "GroupForTest";
    String VALID_DESCRIPTION = "Group01_description";
    Group VALID_GROUP = new Group(VALID_TITLE,VALID_DESCRIPTION);
    ArrayList<Group> VALID_GROUP_LIST = new ArrayList<>();
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;

    //private Group group;

    GroupDaoImpl groupDao;

    @Before
    public void setUp() throws Exception {
        groupDao = new GroupDaoImpl(daoFactory);
        VALID_GROUP_LIST.add(VALID_GROUP);

    }

    @Test
    public void shouldCreateGroup() throws Exception {
        when(connection.prepareStatement(any(String.class), anyInt())).thenReturn(statement);
        when(daoFactory.getConnection()).thenReturn(connection);

        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getString(1)).thenReturn(VALID_GROUP.getTitle());
        when(resultSet.getString(2)).thenReturn(VALID_GROUP.getDescription());
        groupDao.addGroup(VALID_TITLE, VALID_DESCRIPTION);
        verify(daoFactory).getConnection();
    }

    @Test
    public void shouldGetAll() throws SQLException {
        ArrayList<Group> allGroups = new ArrayList<>();
        allGroups.add(VALID_GROUP);

        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(daoFactory.getConnection()).thenReturn(connection);

        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getString("title")).thenReturn(VALID_GROUP.getTitle());
        when(resultSet.getString("description")).thenReturn(VALID_GROUP.getDescription());

        assertEquals(VALID_GROUP_LIST,groupDao.getAll());

    }
    @Test
    public void shouGetAll() {
        DaoFactory daoFactory1 = new DaoFactory();
        GroupDaoImpl groupDao1;
        groupDao1 = new GroupDaoImpl(daoFactory1);
        int i = 5;
        while (i > 0) {
            groupDao1.addGroup(VALID_TITLE, VALID_DESCRIPTION);
            i--;
        }

        ArrayList<Group> allGroups = new ArrayList<>();
        allGroups = groupDao1.getAll();
        for (Group group: allGroups) {
            System.out.println(group.toString());
        }

    }
}
