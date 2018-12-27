package ua.com.foxminded.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentDaoImplTest {
    List<Student> VALID_STUDENT_LIST = new ArrayList<>();

    Student VALID_STUDENT = new Student(1, "student", "testSurname", "male", 25);
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private PreparedStatement statement;
    @Mock
    private Connection connection;
    @Mock
    private ResultSet resultSet;
    @Mock
    StudentDaoImpl mockedStudentDao;

    StudentDaoImpl studentDao;

    @Before
    public void setUp() throws Exception {
       studentDao = new StudentDaoImpl(connectionFactory);
        VALID_STUDENT_LIST.add(VALID_STUDENT);
    }

    @Test
    public void getAll() throws Exception {
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("id")).thenReturn(VALID_STUDENT.getId());
        when(resultSet.getString("name")).thenReturn(VALID_STUDENT.getName());
        when(resultSet.getString("surname")).thenReturn(VALID_STUDENT.getSurName());
        when(resultSet.getString("gender")).thenReturn(VALID_STUDENT.getGender());
        when(resultSet.getInt("age")).thenReturn(VALID_STUDENT.getAge());

        assertEquals(VALID_STUDENT_LIST, studentDao.getAll());
    }

    @Test
    public void getById() throws Exception {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("id")).thenReturn(VALID_STUDENT.getId());
        when(resultSet.getString("name")).thenReturn(VALID_STUDENT.getName());
        when(resultSet.getString("surname")).thenReturn(VALID_STUDENT.getSurName());
        when(resultSet.getString("gender")).thenReturn(VALID_STUDENT.getGender());
        when(resultSet.getInt("age")).thenReturn(VALID_STUDENT.getAge());

        assertEquals(VALID_STUDENT, studentDao.getById(VALID_STUDENT.getId()));
    }

    @Test
    public void shouldInvokeCreate() throws Exception {
        mockedStudentDao.create(anyInt(), anyString(), anyString(), anyString(), anyInt());
        verify(mockedStudentDao).create(anyInt(), anyString(), anyString(), anyString(), anyInt());
    }

    @Test
    public void shouldInvokeUpdate() throws Exception {
        mockedStudentDao.update(anyString(), anyString(), anyString(), anyInt(), anyInt());
        verify(mockedStudentDao).update(anyString(), anyString(), anyString(), anyInt(), anyInt());
    }

    @Test
    public void shouldInvokeDeleteAll() throws Exception {
        mockedStudentDao.deleteAll();
        verify(mockedStudentDao).deleteAll();
    }

    @Test
    public void shouldInvokeDeleteById() throws Exception {
        mockedStudentDao.deleteById(anyInt());
        verify(mockedStudentDao).deleteById(anyInt());
    }
}