package ua.com.foxminded.domain;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.com.foxminded.dao.DaoFactory;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UniversityTest {
    Initialization initialization = new Initialization();
    University VALID_UNIVERSITY = initialization.initializationUniversity();

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
        when(MOCKED_VALID_UNIVERSITY.getStudents()).thenReturn(initialization.VALID_STUDENT_LIST);
        ArrayList<Student> VALID_STUDENT_LIST = MOCKED_VALID_UNIVERSITY.getStudents();
        when(MOCKED_VALID_UNIVERSITY.getTeachers()).thenReturn(initialization.VALID_TEACHERS_LIST);
        ArrayList<Teacher> VALID_TEACHERS_LIST = MOCKED_VALID_UNIVERSITY.getTeachers();
        when(MOCKED_VALID_UNIVERSITY.getGroups()).thenReturn(initialization.VALID_GROUPS_LIST);
        ArrayList<Group> VALID_GROUPS_LIST = MOCKED_VALID_UNIVERSITY.getGroups();
        when(MOCKED_VALID_UNIVERSITY.getLectures()).thenReturn(initialization.VALID_LECTURES_LIST);
        ArrayList<Lecture> VALID_LECTURES_LIST = MOCKED_VALID_UNIVERSITY.getLectures();
        DaoFactory VALID_DAO_FACTORY = VALID_UNIVERSITY.getDaoFactory();

        University actualUniversity = new University(VALID_STUDENT_LIST, VALID_TEACHERS_LIST, VALID_GROUPS_LIST, VALID_LECTURES_LIST, VALID_DAO_FACTORY);

        University expectedUniversity = VALID_UNIVERSITY;
        assertEquals(actualUniversity, expectedUniversity);
    }
}