package ua.com.foxminded.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.dao.ConnectionFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UniversityTest {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    University VALID_UNIVERSITY;
    @Mock
    University MOCKED_VALID_UNIVERSITY;

    @Before
    public void setUp() throws Exception {
        Initialization init = new Initialization();
        VALID_UNIVERSITY = init.initializationUniversity();
    }

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
        List<Student> VALID_STUDENT_LIST = VALID_UNIVERSITY.getStudents();
        when(MOCKED_VALID_UNIVERSITY.getTeachers()).thenReturn(VALID_UNIVERSITY.getTeachers());
        List<Teacher> VALID_TEACHERS_LIST = MOCKED_VALID_UNIVERSITY.getTeachers();
        when(MOCKED_VALID_UNIVERSITY.getGroups()).thenReturn(VALID_UNIVERSITY.getGroups());
        List<Group> VALID_GROUPS_LIST = MOCKED_VALID_UNIVERSITY.getGroups();
        when(MOCKED_VALID_UNIVERSITY.getLectures()).thenReturn(VALID_UNIVERSITY.getLectures());
        List<Lecture> VALID_LECTURES_LIST = MOCKED_VALID_UNIVERSITY.getLectures();

        University actualUniversity = new University(VALID_STUDENT_LIST, VALID_TEACHERS_LIST, VALID_GROUPS_LIST, VALID_LECTURES_LIST, connectionFactory);

        University expectedUniversity = VALID_UNIVERSITY;
        assertEquals(actualUniversity, expectedUniversity);
    }
}