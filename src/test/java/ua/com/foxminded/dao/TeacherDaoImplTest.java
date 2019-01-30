package ua.com.foxminded.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeacherDaoImplTest {
    List<Teacher> VALID_TEACHER_LIST = new ArrayList<>();

    Teacher VALID_TEACHER = new Teacher(1, "teacher", "testSurname", "male", 45);
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    @Mock
    TeacherDaoImpl mockedTeacherDao;

    TeacherDaoImpl teacherDao;

    @Before
    public void setUp() throws DaoException {
        teacherDao = new TeacherDaoImpl();
        VALID_TEACHER_LIST.add(VALID_TEACHER);
    }

    @Test
    public void getAll() throws DaoException, SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("id")).thenReturn(VALID_TEACHER.getId());
        when(resultSet.getString("name")).thenReturn(VALID_TEACHER.getName());
        when(resultSet.getString("surname")).thenReturn(VALID_TEACHER.getSurName());
        when(resultSet.getString("gender")).thenReturn(VALID_TEACHER.getGender());
        when(resultSet.getInt("age")).thenReturn(VALID_TEACHER.getAge());
        when(mockedTeacherDao.getAll()).thenReturn(VALID_TEACHER_LIST);

        List<Teacher> actual_result = VALID_TEACHER_LIST;
        List<Teacher> expected_result = mockedTeacherDao.getAll();
        assertEquals(actual_result, expected_result);

    }

    @Test
    public void getById() throws DaoException, SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("id")).thenReturn(VALID_TEACHER.getId());
        when(resultSet.getString("name")).thenReturn(VALID_TEACHER.getName());
        when(resultSet.getString("surname")).thenReturn(VALID_TEACHER.getSurName());
        when(resultSet.getString("gender")).thenReturn(VALID_TEACHER.getGender());
        when(resultSet.getInt("age")).thenReturn(VALID_TEACHER.getAge());
        when(mockedTeacherDao.getById(anyInt())).thenReturn(VALID_TEACHER);
        Teacher actual_result = VALID_TEACHER;
        Teacher expected_result = mockedTeacherDao.getById(anyInt());
        assertEquals(actual_result, expected_result);
    }

    @Test
    public void shouldInvokeCreate() throws DaoException {
        mockedTeacherDao.create(anyInt(), anyString(), anyString(), anyString(), anyInt());
        verify(mockedTeacherDao).create(anyInt(), anyString(), anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldInvokeUpdate() throws DaoException {
        mockedTeacherDao.update(anyString(), anyString(), anyString(), anyInt(), anyInt());
        verify(mockedTeacherDao).update(anyString(), anyString(), anyString(), anyInt(), anyInt());
    }

    @Test
    public void shouldInvokeDeleteAll() throws DaoException {
        mockedTeacherDao.deleteAll();
        verify(mockedTeacherDao).deleteAll();
    }

    @Test
    public void shouldInvokeDeleteById() throws DaoException {
        mockedTeacherDao.deleteById(anyInt());
        verify(mockedTeacherDao).deleteById(anyInt());
    }
}