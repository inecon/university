package ua.com.foxminded.domain;


import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class ScheduleTest {
    public University VALID_UNIVERSITY;

    public Student VALID_STUDENT1 = new Student("Petro", "Kolhozin", "male", 19);
    public Group VALID_GROUP1 = new Group("Group01", "Spring math group");
    public Student VALID_STUDENT2 = new Student("Tanya", "Pupkina", "female", 18);
    public Group VALID_GROUP2 = new Group("Group02", "Winter linguist group");

    public Teacher VALID_TEACHER1 = new Teacher("Ivan", "Ivanovich", "male", 65);
    public Teacher VALID_TEACHER2 = new Teacher("Nikolai", "Petrovich", "male", 50);

    public Set<String> VALID_SUBJECT_SET1 = new TreeSet<String>();
    public Set<String> VALID_SUBJECT_SET2 = new TreeSet<String>();

    public String VALID_SUBJECT1 = "Math";
    public String VALID_SUBJECT2 = "Biology";
    public String VALID_SUBJECT3 = "Chemistry";
    public String VALID_SUBJECT4 = "Physics";

    public LocalDateTime VALID_DATE1 = LocalDateTime.of(2018, 01, 01, 12, 00);
    public LocalDateTime VALID_DATE2 = LocalDateTime.of(2018, 01, 01, 13, 00);

    public String VALID_SUBJECT = VALID_SUBJECT1;
    public Integer VALID_CLASSROM1 = 10;
    public Integer VALID_CLASSROM2 = 10;

    public Lecture VALID_LECTURE1 = new Lecture(VALID_DATE1, VALID_SUBJECT1, VALID_TEACHER1, VALID_GROUP1, VALID_CLASSROM1);
    public Lecture VALID_LECTURE2 = new Lecture(VALID_DATE2, VALID_SUBJECT2, VALID_TEACHER2, VALID_GROUP2, VALID_CLASSROM2);


    Set<Student> VALID_STUDENT_SET = new TreeSet<Student>();
    Set<Teacher> VALID_TEACHERS_SET = new TreeSet<Teacher>();
    Set<Group> VALID_GROUPS_SET = new TreeSet<Group>();
    Set<Lecture> VALID_LECTURES_SET = new TreeSet<Lecture>();

   @Test
    public void ShouldCreateSchedule(){
       VALID_STUDENT1.setGroup(VALID_GROUP1);
       VALID_STUDENT2.setGroup(VALID_GROUP2);
       VALID_STUDENT_SET.add(VALID_STUDENT1);
       VALID_STUDENT_SET.add(VALID_STUDENT2);

       VALID_SUBJECT_SET1.add(VALID_SUBJECT1);
       VALID_SUBJECT_SET1.add(VALID_SUBJECT2);
       VALID_SUBJECT_SET2.add(VALID_SUBJECT3);
       VALID_SUBJECT_SET2.add(VALID_SUBJECT4);
       VALID_TEACHER1.setSubject(VALID_SUBJECT_SET1);
       VALID_TEACHER2.setSubject(VALID_SUBJECT_SET2);
       VALID_TEACHERS_SET.add(VALID_TEACHER1);
       VALID_TEACHERS_SET.add(VALID_TEACHER2);

       VALID_GROUPS_SET.add(VALID_GROUP1);
       VALID_GROUPS_SET.add(VALID_GROUP2);

       VALID_LECTURES_SET.add(VALID_LECTURE1);
       VALID_LECTURES_SET.add(VALID_LECTURE2);


   }
}