package ua.com.foxminded.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LectureTest {
    public LocalDateTime VALID_DATE = LocalDateTime.of(2000, 01, 01, 12, 00);
    public String VALID_SUBJECT = "Math";
    public Teacher VALID_TEACHER = new Teacher("Ivan", "Ivanovich", "male", 65);
    public Group VALID_GROUP = new Group("Group01", "Spring math group");
    public Integer VALID_CLASSROM = 10;

    public Lecture VALID_LECTURE = new Lecture(VALID_DATE, VALID_SUBJECT, VALID_TEACHER, VALID_GROUP, VALID_CLASSROM);

    @Test
    public void shouldCreateLecture() {
        Lecture actualLecture = mock(Lecture.class);
        actualLecture.setDate(VALID_DATE);
        actualLecture.setSubject(VALID_SUBJECT);
        actualLecture.setTeacher(VALID_TEACHER);
        actualLecture.setGroup(VALID_GROUP);
        actualLecture.setClassroom(VALID_CLASSROM);

        verify(actualLecture).setDate(VALID_DATE);
        verify(actualLecture).setSubject(VALID_SUBJECT);
        verify(actualLecture).setTeacher(VALID_TEACHER);
        verify(actualLecture).setGroup(VALID_GROUP);
        verify(actualLecture).setClassroom(VALID_CLASSROM);
    }

    @Test
    public void shouldCreateValidLecture() {

        Lecture actualLecture = new Lecture();
        actualLecture.setDate(VALID_DATE);
        actualLecture.setSubject(VALID_SUBJECT);
        actualLecture.setTeacher(VALID_TEACHER);
        actualLecture.setGroup(VALID_GROUP);
        actualLecture.setClassroom(VALID_CLASSROM);

        Lecture expectedLecture = VALID_LECTURE;
        assertEquals(actualLecture, expectedLecture);
    }
}