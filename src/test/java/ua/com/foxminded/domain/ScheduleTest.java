package ua.com.foxminded.domain;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class ScheduleTest {
    Initialization initialization = new Initialization();
    University VALID_UNIVERSITY = initialization.VALID_UNIVERSITY;
    Lecture VALID_LECTURE1 = initialization.VALID_LECTURE1;
    Lecture VALID_LECTURE2 = initialization.VALID_LECTURE2;
    Student VALID_STUDENT1 = initialization.VALID_STUDENT1;
    LocalDateTime VALID_DATE2 = initialization.VALID_DATE2;
    LocalDateTime VALID_DATE1 = initialization.VALID_DATE1;
    Teacher VALID_TEACHER1 = initialization.VALID_TEACHER1;

    @Before
    public void universityInitialization() {
        initialization.initializationUniversity();
    }

    @Test
    public void shouldSetUniversity() {
        Schedule actualSchedule = Mockito.mock(Schedule.class);
        actualSchedule.setUniversity(VALID_UNIVERSITY);
        verify(actualSchedule).setUniversity(VALID_UNIVERSITY);
    }

    @Test
    public void shouldGetValidStudentScheduledLectures() {
        // arrange
        Set<Lecture> expectedLectures = new TreeSet<>();
        expectedLectures.add(VALID_LECTURE1);
        Schedule schedule = new Schedule();
        schedule.setUniversity(VALID_UNIVERSITY);

        // act
        Set<Lecture> actualLectures = schedule.getStudentScheduledLectures(VALID_STUDENT1, VALID_DATE2, VALID_DATE1);

        // assert
        assertEquals(actualLectures, expectedLectures);
    }

    @Test
    public void shouldGetValidTeacherScheduledLectures() {
        // arrange
        Set<Lecture> expectedLectures = new TreeSet<>();
        expectedLectures.add(VALID_LECTURE1);
        expectedLectures.add(VALID_LECTURE2);

        Schedule schedule = new Schedule();
        schedule.setUniversity(VALID_UNIVERSITY);

        // act
        Set<Lecture> actualLectures = schedule.getTeacherScheduledLectures(VALID_TEACHER1, VALID_DATE2, VALID_DATE1);

        // assert
        assertEquals(actualLectures, expectedLectures);
    }
}