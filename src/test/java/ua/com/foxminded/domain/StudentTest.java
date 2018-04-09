package ua.com.foxminded.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentTest
{
    public Student VALID_STUDENT = new Student("Petro", "Kolhozin", "male", 19);
    public Group VALID_GROUP = new Group("Group01","Spring math group");

    @Test
    public void shouldCreateStudent() {
        Student actualStudent = mock(Student.class);
        actualStudent.setName("Ivan");
        actualStudent.setSurName("Ivanovich");
        actualStudent.setGender("male");
        actualStudent.setAge(65);
        actualStudent.setGroup(VALID_GROUP);

        verify(actualStudent).setName("Ivan");
        verify(actualStudent).setSurName("Ivanovich");
        verify(actualStudent).setGender("male");
        verify(actualStudent).setAge(65);
        verify(actualStudent).setGroup(VALID_GROUP);
    }

    @Test
    public void shouldCreateValidStudent() {
        Student actualStudent = new Student();
        actualStudent.setName("Petro");
        actualStudent.setSurName("Kolhozin");
        actualStudent.setGender("male");
        actualStudent.setAge(19);

        assertEquals(actualStudent,VALID_STUDENT);
    }
}
