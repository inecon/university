package ua.com.foxminded.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.di.Context;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Initialization;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import static org.mockito.Matchers.any;
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
    LectureDaoImpl mockedLectureDao;

    Context contextForTest;

    LectureDaoImpl lectureDao;// = new LectureDaoImpl(contextForTest) ;


    @Before
    public void setUp() throws Exception {

        Initialization init = new Initialization();
        init.initializationUniversity();
        contextForTest = init.contextForTest;

        /*contextForTest.registerBean("connectionFactory", init.VALID_UNIVERSITY.getConnectionFactory());
        contextForTest.registerBean("VALID_DATE_1", init.VALID_DATE1);
        contextForTest.registerBean("VALID_DATE_2", init.VALID_DATE2);
        contextForTest.registerBean("VALID_TEACHER1", init.VALID_TEACHER1);
        contextForTest.registerBean("VALID_GROUP1", init.VALID_GROUP1);
        contextForTest.registerBean("VALID_CLASSROOM1", init.VALID_CLASSROM1);
        contextForTest.registerBean("VALID_LECTURE_LIST", init.VALID_UNIVERSITY.getLectures());
        contextForTest.registerBean("VALID_SUBJECT1", init.VALID_SUBJECT1);
        contextForTest.registerBean("connection", connection);
        contextForTest.registerBean("resultSet", resultSet);
        contextForTest.registerBean("statement", statement);
        lectureDao = new LectureDaoImpl(contextForTest);
        contextForTest.registerBean("teacherDao", teacherDao);
        contextForTest.registerBean("groupDao", groupDao);
        contextForTest.registerBean("lectureDao", lectureDao);*/
    }

    @Test
    public void contextInit(){
        //System.out.println(contextForTest.getBeanWithScope().get(Student.class));

    }
    @Test
    public void shouldGetAll() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(connectionFactory.getConnection()).thenReturn(connection);
        when(statement.execute()).thenReturn(Boolean.TRUE);
        when(statement.getResultSet()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

       /* when(resultSet.getString("date")).thenReturn(String.valueOf(contextForTest.getBean().get("VALID_DATE_1")));
        when(teacherDao.getById(any())).thenReturn((Teacher) contextForTest.getBean().get("VALID_TEACHER1"));
        when(groupDao.getById(any())).thenReturn((Group) contextForTest.getBean().get("VALID_GROUP1"));
        when(resultSet.getString("subject")).thenReturn((String) contextForTest.getBean().get("VALID_SUBJECT1"));
        when(resultSet.getInt("classroom")).thenReturn((Integer) contextForTest.getBean().get("VALID_CLASSROOM1"));

        assertEquals(contextForTest.getBean().get("VALID_LECTURE_LIST"), ((LectureDaoImpl)contextForTest.getBean().get("lectureDao")).getAll());
        */
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