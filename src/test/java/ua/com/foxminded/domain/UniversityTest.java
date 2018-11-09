package ua.com.foxminded.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.dao.ConnectionFactory;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UniversityTest {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    University VALID_UNIVERSITY;

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
        Set<Student> VALID_STUDENT_LIST = VALID_UNIVERSITY.getStudents();
        Set<Teacher> VALID_TEACHERS_LIST = VALID_UNIVERSITY.getTeachers();
        Set<Group> VALID_GROUPS_LIST = VALID_UNIVERSITY.getGroups();
        Set<Lecture> VALID_LECTURES_LIST = VALID_UNIVERSITY.getLectures();
        connectionFactory = VALID_UNIVERSITY.getConnectionFactory();

        University actualUniversity = new University(VALID_STUDENT_LIST, VALID_TEACHERS_LIST, VALID_GROUPS_LIST, VALID_LECTURES_LIST, connectionFactory);

        University expectedUniversity = VALID_UNIVERSITY;
        assertEquals(actualUniversity, expectedUniversity);
    }
}