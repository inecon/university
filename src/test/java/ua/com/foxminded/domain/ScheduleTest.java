package ua.com.foxminded.domain;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {
    Initialization initialization = new Initialization();
    University VALID_UNIVERSITY = initialization.VALID_UNIVERSITY;
    Lecture VALID_LECTURE1 = initialization.VALID_LECTURE1;
    Lecture VALID_LECTURE2 = initialization.VALID_LECTURE2;
    Student VALID_STUDENT1 = initialization.VALID_STUDENT1;
    LocalDateTime VALID_DATE2 = initialization.VALID_DATE2;
    LocalDateTime VALID_DATE1 = initialization.VALID_DATE1;
    Teacher VALID_TEACHER1 = initialization.VALID_TEACHER1;
    List<Lecture> VALID_STUDENTS_LECTURES = new ArrayList<>();
    List<Lecture> VALID_TEACHERS_LECTURES = new ArrayList<>();

    @Mock
    Schedule schedule;

    @Before
    public void universityInitialization() {
        //initialization.initializationUniversity();
        VALID_STUDENTS_LECTURES.add(VALID_LECTURE1);
        VALID_TEACHERS_LECTURES.add(VALID_LECTURE1);
        VALID_TEACHERS_LECTURES.add(VALID_LECTURE2);
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
        List<Lecture> expectedLectures = new ArrayList<>();
        expectedLectures.add(VALID_LECTURE1);
        when(schedule.getStudentScheduledLectures(any(),any(),any())).thenReturn(VALID_STUDENTS_LECTURES);
        // act
        List<Lecture> actualLectures = schedule.getStudentScheduledLectures(VALID_STUDENT1, VALID_DATE2, VALID_DATE1);
        // assert
        assertEquals(actualLectures, expectedLectures);
    }

    @Test
    public void shouldGetValidTeacherScheduledLectures() {
        // arrange
        List<Lecture> expectedLectures = new ArrayList<>();
        expectedLectures.add(VALID_LECTURE1);
        expectedLectures.add(VALID_LECTURE2);
        when(schedule.getTeacherScheduledLectures(any(),any(),any())).thenReturn(VALID_TEACHERS_LECTURES);
        // act
        List<Lecture> actualLectures = schedule.getTeacherScheduledLectures(VALID_TEACHER1, VALID_DATE2, VALID_DATE1);
        // assert
        assertEquals(actualLectures, expectedLectures);
    }
}