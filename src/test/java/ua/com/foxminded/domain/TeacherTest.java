package ua.com.foxminded.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeacherTest {

    public Teacher VALID_TEACHER = new Teacher(1, "Ivan", "Ivanovich", "male", 65);

    @Test
    public void shouldCreateTeacher() {
        Teacher actualTeacher = mock(Teacher.class);
        actualTeacher.setId(1);
        actualTeacher.setName("Ivan");
        actualTeacher.setSurName("Ivanovich");
        actualTeacher.setGender("male");
        actualTeacher.setAge(65);
        actualTeacher.setSubject(anyString());

        verify(actualTeacher).setId(1);
        verify(actualTeacher).setName("Ivan");
        verify(actualTeacher).setSurName("Ivanovich");
        verify(actualTeacher).setGender("male");
        verify(actualTeacher).setAge(65);
        verify(actualTeacher).setSubject(anyString());
    }

    @Test
    public void shouldCreateValidTeacher() {
        Teacher actualTeacher = new Teacher();
        actualTeacher.setId(1);
        actualTeacher.setName("Ivan");
        actualTeacher.setSurName("Ivanovich");
        actualTeacher.setGender("male");
        actualTeacher.setAge(65);
        actualTeacher.setSubject("Math");
        actualTeacher.setSubject("Biology");
        VALID_TEACHER.setSubject("Math");
        VALID_TEACHER.setSubject("Biology");
        Teacher expectedTeacher = VALID_TEACHER;
        assertEquals(actualTeacher, expectedTeacher);
    }
}