package ua.com.foxminded.domain;

import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.di.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Initialization {
    private static final Object Student = new Student(1,"Petro", "Kolhozin", "male", 19);
    public Student VALID_STUDENT1 = new Student(1,"Petro", "Kolhozin", "male", 19);
    public Group VALID_GROUP1 = new Group(1,"Group01", "Spring math group");

    public Student VALID_STUDENT2 = new Student(2,"Tanya", "Pupkina", "female", 18);
    public Group VALID_GROUP2 = new Group(2,"Group02", "Winter linguist group");

    public Teacher VALID_TEACHER1 = new Teacher(1,"Ivan", "Ivanovich", "male", 65);
    public Teacher VALID_TEACHER2 = new Teacher(2,"Nikolai", "Petrovich", "male", 50);

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

    ArrayList<Student> VALID_STUDENT_LIST = new ArrayList<>();
    ArrayList<Teacher> VALID_TEACHERS_LIST = new ArrayList<>();
    ArrayList<Group> VALID_GROUPS_LIST = new ArrayList<>();
    ArrayList<Lecture> VALID_LECTURES_LIST = new ArrayList<>();
    public University VALID_UNIVERSITY = new University();

    ConnectionFactory VALID_DAO_FACTORY = new ConnectionFactory();
    public Context contextForTest = new Context();

    public University initializationUniversity() throws InstantiationException, IllegalAccessException, ClassNotFoundException {

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

        contextForTest.registerBean("connectionFactory", VALID_UNIVERSITY.getConnectionFactory());
        contextForTest.registerBean("VALID_DATE_1", VALID_DATE1);
        contextForTest.registerBean("VALID_DATE_2", VALID_DATE2);
        contextForTest.registerBean("VALID_TEACHER1", VALID_TEACHER1);
        contextForTest.registerBean("VALID_GROUP1", VALID_GROUP1);
        contextForTest.registerBean("VALID_CLASSROOM1", VALID_CLASSROM1);
        contextForTest.registerBean("VALID_LECTURE_LIST", VALID_UNIVERSITY.getLectures());
        contextForTest.registerBean("VALID_SUBJECT1", VALID_SUBJECT1);

        //contextForTest.registerBean(Group.class, Context.Scope.singlton);

        return VALID_UNIVERSITY;
    }
}
