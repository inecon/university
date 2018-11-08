package ua.com.foxminded.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Schedule {
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.university + "\n");
        return result.toString();
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
