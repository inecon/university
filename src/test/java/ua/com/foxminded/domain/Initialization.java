package ua.com.foxminded.domain;

import ua.com.foxminded.dao.ConnectionFactory;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Initialization {
    public Group VALID_GROUP1 = new Group(1,"Group01", "Spring math group");
    public Student VALID_STUDENT1 = new Student(1,"Petro", "Kolhozin", "male", 19, VALID_GROUP1);

    public Group VALID_GROUP2 = new Group(2,"Group02", "Winter linguist group");
    public Student VALID_STUDENT2 = new Student(2,"Tanya", "Pupkina", "female", 18, VALID_GROUP2);

    public String VALID_SUBJECT1 = "Math";
    public String VALID_SUBJECT2 = "Biology";
    public String VALID_SUBJECT3 = "Chemistry";
    public String VALID_SUBJECT4 = "Physics";

    public Teacher VALID_TEACHER1 = new Teacher(1,"Ivan", "Ivanovich", "male", 65, VALID_SUBJECT1);
    public Teacher VALID_TEACHER2 = new Teacher(2,"Nikolai", "Petrovich", "male", 50, VALID_SUBJECT2);

    public Integer VALID_ID1 = 1;
    public Integer VALID_ID2 = 2;



    public LocalDateTime VALID_DATE1 = LocalDateTime.of(2018, 01, 01, 12, 00);
    public LocalDateTime VALID_DATE2 = LocalDateTime.of(2018, 01, 01, 13, 00);

    public Integer VALID_CLASSROOM1 = 10;
    public Integer VALID_CLASSROOM2 = 11;

    public Lecture VALID_LECTURE1 = new Lecture(VALID_ID1, VALID_DATE1, VALID_SUBJECT1, VALID_TEACHER1, VALID_GROUP1, VALID_CLASSROOM1);
    public Lecture VALID_LECTURE2 = new Lecture(VALID_ID2, VALID_DATE2, VALID_SUBJECT2, VALID_TEACHER2, VALID_GROUP2, VALID_CLASSROOM2);

    Set<Student> VALID_STUDENT_LIST = new HashSet<>();
    Set<Teacher> VALID_TEACHERS_LIST = new HashSet<>();
    Set<Group> VALID_GROUPS_LIST = new HashSet<>();
    Set<Lecture> VALID_LECTURES_LIST = new HashSet<>();
    public University VALID_UNIVERSITY = new University();

    ConnectionFactory VALID_DAO_FACTORY = new ConnectionFactory();

    public University initializationUniversity() {

        VALID_STUDENT1.setGroup(VALID_GROUP1);
        VALID_STUDENT2.setGroup(VALID_GROUP2);
        VALID_STUDENT_LIST.add(VALID_STUDENT1);
        VALID_STUDENT_LIST.add(VALID_STUDENT2);

        VALID_TEACHER1.setSubject(VALID_SUBJECT1);
        VALID_TEACHER1.setSubject(VALID_SUBJECT2);

        VALID_TEACHER2.setSubject(VALID_SUBJECT3);
        VALID_TEACHER2.setSubject(VALID_SUBJECT4);
        VALID_TEACHERS_LIST.add(VALID_TEACHER1);
        VALID_TEACHERS_LIST.add(VALID_TEACHER2);

        VALID_GROUPS_LIST.add(VALID_GROUP1);
        VALID_GROUPS_LIST.add(VALID_GROUP2);

        VALID_LECTURES_LIST.add(VALID_LECTURE1);
        VALID_LECTURES_LIST.add(VALID_LECTURE2);

        VALID_UNIVERSITY.setStudents(VALID_STUDENT_LIST);
        VALID_UNIVERSITY.setTeachers(VALID_TEACHERS_LIST);
        VALID_UNIVERSITY.setGroups(VALID_GROUPS_LIST);
        VALID_UNIVERSITY.setLectures(VALID_LECTURES_LIST);
        VALID_UNIVERSITY.setConnectionFactory(VALID_DAO_FACTORY);

        return VALID_UNIVERSITY;
    }
}
