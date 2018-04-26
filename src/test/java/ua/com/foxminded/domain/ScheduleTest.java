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
    public Student VALID_STUDENT1 = new Student("Petro", "Kolhozin", "male", 19);
    public Group VALID_GROUP1 = new Group("Group01", "Spring math group");
    public Student VALID_STUDENT2 = new Student("Tanya", "Pupkina", "female", 18);
    public Group VALID_GROUP2 = new Group("Group02", "Winter linguist group");

    public Teacher VALID_TEACHER1 = new Teacher("Ivan", "Ivanovich", "male", 65);
    public Teacher VALID_TEACHER2 = new Teacher("Nikolai", "Petrovich", "male", 50);

    public String VALID_SUBJECT1 = "Math";
    public String VALID_SUBJECT2 = "Biology";
    public String VALID_SUBJECT3 = "Chemistry";
    public String VALID_SUBJECT4 = "Physics";

    public LocalDateTime VALID_DATE1 = LocalDateTime.of(2018, 01, 01, 12, 00);
    public LocalDateTime VALID_DATE2 = LocalDateTime.of(2018, 01, 01, 13, 00);

    public Integer VALID_CLASSROM1 = 10;
    public Integer VALID_CLASSROM2 = 11;

    public Lecture VALID_LECTURE1 = new Lecture(VALID_DATE1, VALID_SUBJECT1, VALID_TEACHER1, VALID_GROUP1, VALID_CLASSROM1);
    public Lecture VALID_LECTURE2 = new Lecture(VALID_DATE2, VALID_SUBJECT2, VALID_TEACHER2, VALID_GROUP2, VALID_CLASSROM2);

    Set<Student> VALID_STUDENT_SET = new TreeSet<Student>();
    Set<Teacher> VALID_TEACHERS_SET = new TreeSet<Teacher>();
    Set<Group> VALID_GROUPS_SET = new TreeSet<Group>();
    Set<Lecture> VALID_LECTURES_SET = new TreeSet<Lecture>();

    public University VALID_UNIVERSITY = new University();

    @Before
    public void initializationUniversity() {
        VALID_STUDENT1.setGroup(VALID_GROUP1);
        VALID_STUDENT2.setGroup(VALID_GROUP2);
        VALID_STUDENT_SET.add(VALID_STUDENT1);
        VALID_STUDENT_SET.add(VALID_STUDENT2);

        VALID_TEACHER1.setSubject(VALID_SUBJECT1);
        VALID_TEACHER1.setSubject(VALID_SUBJECT2);

        VALID_TEACHER2.setSubject(VALID_SUBJECT3);
        VALID_TEACHER2.setSubject(VALID_SUBJECT4);
        VALID_TEACHERS_SET.add(VALID_TEACHER1);
        VALID_TEACHERS_SET.add(VALID_TEACHER2);

        VALID_GROUPS_SET.add(VALID_GROUP1);
        VALID_GROUPS_SET.add(VALID_GROUP2);

        VALID_LECTURES_SET.add(VALID_LECTURE1);
        VALID_LECTURES_SET.add(VALID_LECTURE2);

        VALID_UNIVERSITY.setStudents(VALID_STUDENT_SET);
        VALID_UNIVERSITY.setTeachers(VALID_TEACHERS_SET);
        VALID_UNIVERSITY.setGroups(VALID_GROUPS_SET);
        VALID_UNIVERSITY.setLectures(VALID_LECTURES_SET);
    }

    @Test
    public void ShouldSetUniversity() {
        Schedule actualSchedule = Mockito.mock(Schedule.class);
        actualSchedule.setUniversity(VALID_UNIVERSITY);
        verify(actualSchedule).setUniversity(VALID_UNIVERSITY);
    }

    @Test
    public void shouldGetScheduledLectures() {
        Schedule actualSchedule = new Schedule();
        actualSchedule.setUniversity(VALID_UNIVERSITY);
        Set<Lecture> expectedLectures = new TreeSet<Lecture>();
        expectedLectures.add(VALID_LECTURE1);
        assertEquals(actualSchedule.getScheduledLectures(VALID_STUDENT1, VALID_DATE2, VALID_DATE1), expectedLectures);
    }
}