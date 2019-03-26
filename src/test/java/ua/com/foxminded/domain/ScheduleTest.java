package ua.com.foxminded.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.foxminded.repository.LectureDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {
    public Group VALID_GROUP1 = new Group(1, "Group01", "Spring math group");
    public Student VALID_STUDENT1 = new Student(1, "Petro", "Kolhozin", "male", 19, VALID_GROUP1);

    public String VALID_SUBJECT1 = "Math";

    public Teacher VALID_TEACHER1 = new Teacher(1, "Ivan", "Ivanovich", "male", 65, VALID_SUBJECT1);

    public Integer VALID_ID1 = 1;

    public LocalDateTime VALID_NOW_DATE = LocalDateTime.now();

    public Integer VALID_CLASSROOM1 = 10;

    public Lecture VALID_LECTURE1 = new Lecture(VALID_ID1, VALID_NOW_DATE, VALID_SUBJECT1, VALID_TEACHER1, VALID_GROUP1, VALID_CLASSROOM1);

    public List<Lecture> VALID_LECTURES = new ArrayList<>();

    @Mock
    Lecture lecture;

    @Mock
    LectureDao lectureDao;

    @InjectMocks
    Schedule sut;

    @Before
    public void init() {
        VALID_LECTURES.add(VALID_LECTURE1);
    }

    @Test
    public void shouldGetValidStudentScheduledLecturesMonth() {
        // arrange
        Mockito.when(lectureDao.findAll()).thenReturn(VALID_LECTURES);
        List<Lecture> expectedLectures = VALID_LECTURES;
        // act
        List<Lecture> actualLectures = sut.getStudentScheduledLecturesMonth(VALID_STUDENT1);
        // assert
        assertEquals(actualLectures, expectedLectures);
    }

    @Test
    public void shouldGetValidTeacherScheduledLecturesMonth() {
        // arrange
        Mockito.when(lectureDao.findAll()).thenReturn(VALID_LECTURES);
        List<Lecture> expectedLectures = VALID_LECTURES;
        // act
        List<Lecture> actualLectures = sut.getTeacherScheduledLecturesMonth(VALID_TEACHER1);
        // assert
        assertEquals(actualLectures, expectedLectures);
    }
}