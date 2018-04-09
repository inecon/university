package ua.com.foxminded.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TeacherTest {

    public Teacher VALID_TEACHER = new Teacher("Ivan", "Ivanovich", "male", 65);

    @Test
    public void shouldCreateTeacher() {
        Teacher actualTeacher = mock(Teacher.class);
        actualTeacher.setName("Ivan");
        actualTeacher.setSurName("Ivanovich");
        actualTeacher.setGender("male");
        actualTeacher.setAge(65);
        actualTeacher.setSubject(anySet());

        verify(actualTeacher).setName("Ivan");
        verify(actualTeacher).setSurName("Ivanovich");
        verify(actualTeacher).setGender("male");
        verify(actualTeacher).setAge(65);
        verify(actualTeacher).setSubject(anySet());
    }

    @Test
    public void shouldCreateValidTeacher() {
        Teacher actualTeacher = new Teacher();
        actualTeacher.setName("Ivan");
        actualTeacher.setSurName("Ivanovich");
        actualTeacher.setGender("male");
        actualTeacher.setAge(65);


        assertEquals(actualTeacher,VALID_TEACHER);
    }
}