package ua.com.foxminded.domain;


import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UniversityTest {
    Initialization initialization = new Initialization();
    University VALID_UNIVERSITY = initialization.VALID_UNIVERSITY;

    @Test
    public void shouldCreateUniversity() {
        University actualUniversity = mock(University.class);

        actualUniversity.setStudents(anySet());
        actualUniversity.setTeachers(anySet());
        actualUniversity.setGroups(anySet());
        actualUniversity.setLectures(anySet());

        verify(actualUniversity).setStudents(anySet());
        verify(actualUniversity).setTeachers(anySet());
        verify(actualUniversity).setGroups(anySet());
        verify(actualUniversity).setLectures(anySet());
    }

    @Test
    public void shouldCreateValidUniversity() {
        Set<Student> VALID_STUDENT_SET = VALID_UNIVERSITY.getStudents();
        Set<Teacher> VALID_TEACHERS_SET = VALID_UNIVERSITY.getTeachers();
        Set<Group> VALID_GROUPS_SET = VALID_UNIVERSITY.getGroups();
        Set<Lecture> VALID_LECTURES_SET = VALID_UNIVERSITY.getLectures();
        University actualUniversity = new University(VALID_STUDENT_SET, VALID_TEACHERS_SET, VALID_GROUPS_SET, VALID_LECTURES_SET);

        University expectedUniversity = VALID_UNIVERSITY;
        assertEquals(actualUniversity, expectedUniversity);
    }
}