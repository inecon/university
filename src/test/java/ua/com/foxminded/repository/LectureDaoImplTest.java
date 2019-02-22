package ua.com.foxminded.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LectureDaoImplTest {
    Integer VALID_ID = 1;
    LocalDateTime VALID_DATE_TIME = LocalDateTime.of(2018, Month.JANUARY, 1, 12, 00);
    String VALID_SUBJECT = "Test subject";
    Teacher VALID_TEACHER = new Teacher(1, "Ivan", "Ivanovich", "male", 65, VALID_SUBJECT);
    Group VALID_GROUP = new Group(1, "Group01", "Math");
    Integer VALID_CLASSROOM = 1;
    Lecture VALID_LECTURE = new Lecture(VALID_ID, VALID_DATE_TIME, VALID_SUBJECT, VALID_TEACHER, VALID_GROUP, VALID_CLASSROOM);
    List<Lecture> VALID_LECTURE_LIST = new ArrayList<>();
    @Mock
    private ConnectionFactory connectionFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private GroupDao groupDao;
    @Mock
    private TeacherDao teacherDao;
    @Mock
    private LectureDaoImpl mockedLectureDao;

    @Before
    public void setUp() throws DaoException {
        VALID_LECTURE_LIST.add(VALID_LECTURE);
    }

    @Test
    public void shouldById() throws DaoException, SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        when(resultSet.getInt("id")).thenReturn((VALID_LECTURE.getId()));
        when(resultSet.getString("date")).thenReturn(String.valueOf(VALID_LECTURE.getDate()));
        when(teacherDao.getById(any())).thenReturn(VALID_LECTURE.getTeacher());
        when(groupDao.getById(any())).thenReturn(VALID_LECTURE.getGroup());
        when(resultSet.getString("subject")).thenReturn(VALID_LECTURE.getSubject());
        when(resultSet.getInt("classroom")).thenReturn(VALID_LECTURE.getClassroom());
        when(mockedLectureDao.getById(anyInt())).thenReturn(VALID_LECTURE);

        Lecture actualResult = VALID_LECTURE;
        Lecture expectedResult = mockedLectureDao.getById(anyInt());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldGetGetAll() throws DaoException, SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getDataSourceConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        when(resultSet.getInt("id")).thenReturn((VALID_LECTURE.getId()));
        when(resultSet.getString("date")).thenReturn(String.valueOf(VALID_LECTURE.getDate()));
        when(teacherDao.getById(any())).thenReturn(VALID_LECTURE.getTeacher());
        when(groupDao.getById(any())).thenReturn(VALID_LECTURE.getGroup());
        when(resultSet.getString("subject")).thenReturn(VALID_LECTURE.getSubject());
        when(resultSet.getInt("classroom")).thenReturn(VALID_LECTURE.getClassroom());
        when(mockedLectureDao.getAll()).thenReturn(VALID_LECTURE_LIST);

        List<Lecture> actualResult = VALID_LECTURE_LIST;
        List<Lecture> expectedResult = mockedLectureDao.getAll();
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void shouldInvokeCreate() throws DaoException {
        mockedLectureDao.create(any());
        verify(mockedLectureDao).create(any());
    }

    @Test
    public void shouldInvokeUpdate() throws DaoException {
        mockedLectureDao.update(any());
        verify(mockedLectureDao).update(any());
    }

    @Test
    public void shouldInvokeDeleteById() throws DaoException {
        mockedLectureDao.delete(any());
        verify(mockedLectureDao).delete(any());
    }

}