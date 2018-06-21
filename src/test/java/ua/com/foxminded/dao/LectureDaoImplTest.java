package ua.com.foxminded.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LectureDaoImplTest {
    LocalDateTime VALID_DATE_TIME = LocalDateTime.of(2018, Month.JANUARY, 1, 12, 00);
    String VALID_SUBJECT = "Test subject";
    Teacher VALID_TEACHER = new Teacher(1, "Ivan", "Ivanovich", "male", 65);
    Group VALID_GROUP = new Group(1, "Group01", "Math");
    Integer VALID_CLASSROOM = 1;
    Lecture VALID_LECTURE = new Lecture(VALID_DATE_TIME, VALID_SUBJECT, VALID_TEACHER, VALID_GROUP, VALID_CLASSROOM);
    ArrayList<Lecture> VALID_LECTURE_LIST = new ArrayList<>();
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement statement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private GroupDao groupDao;
    @Mock
    private TeacherDaoImpl teacherDao;
    @Mock
    LectureDaoImpl mockedLectureDao;

    LectureDaoImpl lectureDao;


    @Before
    public void setUp() throws Exception {
        lectureDao = new LectureDaoImpl(daoFactory);
        VALID_LECTURE_LIST.add(VALID_LECTURE);
    }

    @Test
    public void shouldGetAllByDate() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(daoFactory.getConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(resultSet.getInt("teacher_id")).thenReturn(VALID_TEACHER.getId());

        when(resultSet.getInt("group_id")).thenReturn(VALID_GROUP.getId());
        when(teacherDao.getById(anyInt())).thenReturn(VALID_TEACHER);
        when(groupDao.getById(anyInt())).thenReturn(VALID_GROUP);
        when(resultSet.getString("date")).thenReturn(String.valueOf(VALID_DATE_TIME));
        when(resultSet.getString("subject")).thenReturn(VALID_SUBJECT);
        when(resultSet.getInt("classroom")).thenReturn(VALID_CLASSROOM);

        assertEquals(VALID_LECTURE_LIST, lectureDao.getAllByDate(VALID_DATE_TIME));
    }


    @Test
    public void shouldInvokeCreate() {
        mockedLectureDao.create(any());
        verify(mockedLectureDao).create(any());
    }

    @Test
    public void shouldInvokeUpdate() {
        mockedLectureDao.update(any());
        verify(mockedLectureDao).update(any());
    }

    @Test
    public void shouldInvokeDeleteAll() {
        mockedLectureDao.deleteAll();
        verify(mockedLectureDao).deleteAll();
    }
}