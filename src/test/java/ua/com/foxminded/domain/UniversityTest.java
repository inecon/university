package ua.com.foxminded.domain;


import org.junit.Test;

import java.time.LocalDateTime;

import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UniversityTest {
    public Student VALID_STUDENT = new Student("Petro", "Kolhozin", "male", 19);
    public Teacher VALID_TEACHER = new Teacher("Ivan", "Ivanovich", "male", 65);
    public Group VALID_GROUP = new Group("Group01","Spring math group");

    public LocalDateTime VALID_DATE = LocalDateTime.of(2000, 01, 01, 12, 00);
    public String VALID_SUBJECT = "Math";
    public Integer VALID_CLASSROM = 10;
    public Lecture VALID_LECTURE = new Lecture(VALID_DATE, VALID_SUBJECT, VALID_TEACHER, VALID_GROUP, VALID_CLASSROM);

    @Test
    public void ShouldCreateUniversity() {
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
}