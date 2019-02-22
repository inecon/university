package ua.com.foxminded.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.repository.LectureDao;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Schedule {
    List<Lecture> studentSchedule = new ArrayList<>();
    List<Lecture> teacherSchedule = new ArrayList<>();

    @Inject
    Lecture lecture;

    @Inject
    LectureDao lectureDao;

    //Method returns students schedule to current month
    public List<Lecture> getStudentScheduledLecturesMonth(Student student) {
        Group groupToFindSchedule = student.getGroup();
        List<Lecture> allScheduleLectures = lectureDao.getAll();
        for (Lecture lecture : allScheduleLectures) {
            if (lecture.getGroup().equals(groupToFindSchedule) &&
                    (lecture.getDate().getMonth()).equals(LocalDateTime.now().getMonth())) {
                studentSchedule.add(lecture);
            }
        }
        return studentSchedule;
    }

    //Method returns teachers schedule to current month
    public List<Lecture> getTeacherScheduledLecturesMonth(Teacher teacher) {
        Integer idToFindSchedule = teacher.getId();
        List<Lecture> allScheduleLectures = lectureDao.getAll();
        for (Lecture lecture : allScheduleLectures) {
            if (lecture.getTeacher().getId().equals(idToFindSchedule) &&
                    (lecture.getDate().getMonth()).equals(LocalDateTime.now().getMonth())) {
                teacherSchedule.add(lecture);
            }
        }
        return teacherSchedule;
    }
}
