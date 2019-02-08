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
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupDaoImplTest {
    Integer VALID_ID = 1;
    String VALID_TITLE = "GroupForTest";
    String VALID_DESCRIPTION = "Group01_description";
    Group VALID_GROUP = new Group(VALID_ID, VALID_TITLE, VALID_DESCRIPTION);
    List<Group> VALID_GROUP_LIST = new ArrayList<>();

    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    @Mock
    GroupDaoImpl mockedGroupDao;

    GroupDaoImpl groupDao;

    @Before
    public void setUp() throws DaoException {
        groupDao = new GroupDaoImpl();
        VALID_GROUP_LIST.add(VALID_GROUP);
    }

    @Test
    public void shouldGetAll() throws SQLException, DaoException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        when(resultSet.getInt("id")).thenReturn(VALID_ID);
        when(resultSet.getString("title")).thenReturn(String.valueOf(VALID_TITLE));
        when(resultSet.getString("description")).thenReturn(VALID_DESCRIPTION);
        when(mockedGroupDao.getAll()).thenReturn(VALID_GROUP_LIST);

        List<Group> actualResult = VALID_GROUP_LIST;
        List<Group> expectedResult = mockedGroupDao.getAll();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldGetId() throws SQLException, DaoException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        when(resultSet.getInt("id")).thenReturn(VALID_ID);
        when(resultSet.getString("title")).thenReturn(String.valueOf(VALID_TITLE));
        when(resultSet.getString("description")).thenReturn(VALID_DESCRIPTION);
        when(mockedGroupDao.getById(anyInt())).thenReturn(VALID_GROUP);

        Group actualResult = VALID_GROUP;
        Group expectedResult = mockedGroupDao.getById(anyInt());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldInvokeCreate() throws DaoException {
        mockedGroupDao.create(any());
        verify(mockedGroupDao).create(any());
    }

    @Test
    public void shouldInvokeGetAll() throws DaoException{
        mockedGroupDao.getAll();
        verify(mockedGroupDao).getAll();
    }

    @Test
    public void shouldInvokeGetById() throws DaoException{
        mockedGroupDao.getById(anyInt());
        verify(mockedGroupDao).getById(anyInt());
    }

    @Test
    public void shouldInvokeUpdate() throws DaoException {
        mockedGroupDao.update(any());
        verify(mockedGroupDao).update(any());
    }

    @Test
    public void shouldInvokeDeleteById() throws DaoException {
        mockedGroupDao.deleteById(anyInt());
        verify(mockedGroupDao).deleteById(anyInt());
    }
}
