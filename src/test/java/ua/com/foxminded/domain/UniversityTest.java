package ua.com.foxminded.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.di.Context;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UniversityTest {
    Context contextForTest = new Context();



    @Before
    public void setUp() throws Exception {
        Initialization init = new Initialization();
        init.initializationUniversity();

        contextForTest.registerBean("connectionFactory", init.VALID_UNIVERSITY.getConnectionFactory());
        contextForTest.registerBean("VALID_DATE_1", init.VALID_DATE1);
        contextForTest.registerBean("VALID_DATE_2", init.VALID_DATE2);
        contextForTest.registerBean("VALID_TEACHER1", init.VALID_TEACHER1);
        contextForTest.registerBean("VALID_GROUP1", init.VALID_GROUP1);
        contextForTest.registerBean("VALID_CLASSROOM1", init.VALID_CLASSROM1);
        contextForTest.registerBean("VALID_LECTURE_LIST", init.VALID_UNIVERSITY.getLectures());
        contextForTest.registerBean("VALID_STUDENTS_LIST", init.VALID_UNIVERSITY.getStudents());
        contextForTest.registerBean("VALID_TEACHERS_LIST", init.VALID_UNIVERSITY.getTeachers());
        contextForTest.registerBean("VALID_GROUPS_LIST", init.VALID_UNIVERSITY.getGroups());

        contextForTest.registerBean("VALID_SUBJECT1", init.VALID_SUBJECT1);
        //contextForTest.registerBean("connection", connection);
        //contextForTest.registerBean("resultSet", resultSet);
       // contextForTest.registerBean("statement", statement);
    }

    @Mock
    University MOCKED_VALID_UNIVERSITY;

    @Test
    public void shouldCreateUniversity() {
        University actualUniversity = mock(University.class);

        actualUniversity.setStudents(any());
        actualUniversity.setTeachers(any());
        actualUniversity.setGroups(any());
        actualUniversity.setLectures(any());

        verify(actualUniversity).setStudents(any());
        verify(actualUniversity).setTeachers(any());
        verify(actualUniversity).setGroups(any());
        verify(actualUniversity).setLectures(any());
    }

    @Test
    public void shouldCreateValidUniversity() {
        when(MOCKED_VALID_UNIVERSITY.getStudents()).thenReturn((ArrayList<Student>) contextForTest.getBean().get("VALID_STUDENTS_LIST"));
        ArrayList<Student> VALID_STUDENT_LIST = MOCKED_VALID_UNIVERSITY.getStudents();
        when(MOCKED_VALID_UNIVERSITY.getTeachers()).thenReturn((ArrayList<Teacher>) contextForTest.getBean().get("VALID_TEACHERS_LIST"));
        ArrayList<Teacher> VALID_TEACHERS_LIST = MOCKED_VALID_UNIVERSITY.getTeachers();
        when(MOCKED_VALID_UNIVERSITY.getGroups()).thenReturn((ArrayList<Group>) contextForTest.getBean().get("VALID_GROUPS_LIST"));
        ArrayList<Group> VALID_GROUPS_LIST = MOCKED_VALID_UNIVERSITY.getGroups();
        when(MOCKED_VALID_UNIVERSITY.getLectures()).thenReturn((ArrayList<Lecture>) contextForTest.getBean().get("VALID_LECTURES_LIST"));
        ArrayList<Lecture> VALID_LECTURES_LIST = MOCKED_VALID_UNIVERSITY.getLectures();
        Context VALID_CONTEXT = contextForTest;

        University actualUniversity = new University(VALID_STUDENT_LIST, VALID_TEACHERS_LIST, VALID_GROUPS_LIST, VALID_LECTURES_LIST, VALID_CONTEXT);

        University expectedUniversity = (University) contextForTest.getBean().get("VALID_UNIVERSITY");
        assertEquals(actualUniversity, expectedUniversity);
    }
}