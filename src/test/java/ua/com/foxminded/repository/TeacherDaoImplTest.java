package ua.com.foxminded.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.domain.Teacher;
import ua.com.foxminded.exceptions.MyException;

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

    Teacher VALID_TEACHER = new Teacher(1, "teacher", "testSurname", "male", 45, "Math");
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
    public void setUp() throws MyException {
        teacherDao = new TeacherDaoImpl();
        VALID_TEACHER_LIST.add(VALID_TEACHER);
    }

    @Test
    public void getAll() throws MyException, SQLException {
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
    public void getById() throws MyException, SQLException {
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
    public void shouldInvokeCreate() throws MyException {
        mockedTeacherDao.create(VALID_TEACHER);
        verify(mockedTeacherDao).create(VALID_TEACHER);
    }

    @Test
    public void shouldInvokeUpdate() throws MyException {
        mockedTeacherDao.update(VALID_TEACHER);
        verify(mockedTeacherDao).update(VALID_TEACHER);
    }

    @Test
    public void shouldInvokeDeleteById() throws MyException {
        mockedTeacherDao.delete(any());
        verify(mockedTeacherDao).delete(any());
    }
}