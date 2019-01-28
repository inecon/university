package ua.com.foxminded.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@ToString
public class Schedule {
    @Setter
    @Getter
    private University university;

    public List<Lecture> getStudentScheduledLectures(Student student, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        List<Lecture> result = new ArrayList<>();
        Group groupToFindSchedule = student.getGroup();

        Set<Lecture> allScheduleLectures = university.getLectures();
        for (Lecture lecture : allScheduleLectures) {
            if (lecture.getGroup().equals(groupToFindSchedule)) {
                result.add(lecture);
            }
        }
        return result;
    }

    public List<Lecture> getTeacherScheduledLectures(Teacher teacher, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        List<Lecture> result = new ArrayList<>();
        List<String> subjectToFindSchedule = teacher.getSubject();

        Set<Lecture> allScheduleLectures = university.getLectures();
        for (Lecture lecture : allScheduleLectures) {
            for (String subject : subjectToFindSchedule) {
                if (lecture.getSubject().equals(subject)) {
                    result.add(lecture);
                }
            }
        }
        return result;
    }
}
