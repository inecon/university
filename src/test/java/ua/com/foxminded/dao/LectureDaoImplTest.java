package ua.com.foxminded.dao;

import org.junit.Test;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Teacher;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class LectureDaoImplTest {
    LocalDateTime VALID_DATE_TIME = LocalDateTime.of(2018, Month.JANUARY, 1, 12, 00);
    String VALID_SUBJECT = "Test subject";
    Teacher VALID_TEACHER = new Teacher(1,"Ivan", "Ivanovich", "male", 65);
    Group VALID_GROUP = new Group(1, "Group01", "Math");
    Integer VALID_CLASSROOM = 1;
    Lecture VALID_LECTURE = new Lecture(VALID_DATE_TIME, VALID_SUBJECT, VALID_TEACHER, VALID_GROUP, VALID_CLASSROOM);


    @Test
    public void getAllByDate() {
        DaoFactory daoFactory = new DaoFactory();
        LectureDaoImpl lectureDao;
        lectureDao = new LectureDaoImpl(daoFactory);
        int i = 5;
        while (i > 0) {
            lectureDao.addLecture(VALID_LECTURE);
            i--;
        }

        ArrayList<Lecture> allLectures = new ArrayList<>();
        allLectures = lectureDao.getAllByDate(VALID_DATE_TIME);
        for (Lecture lecture : allLectures) {
            System.out.println(lecture.toString());
        }
    }

    @Test
    public void addLecture() {
        DaoFactory daoFactory = new DaoFactory();
        LectureDaoImpl lectureDao;
        lectureDao = new LectureDaoImpl(daoFactory);
        lectureDao.addLecture(VALID_LECTURE);
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}