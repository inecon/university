package ua.com.foxminded.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private University university;

    public ArrayList<Lecture> getStudentScheduledLectures(Student student, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        ArrayList<Lecture> result = new ArrayList<>();
        Group groupToFindSchedule = student.getGroup();

        List<Lecture> allScheduleLectures = university.getLectures();
        for (Lecture lecture : allScheduleLectures) {
            if (lecture.getGroup().equals(groupToFindSchedule)) {
                result.add(lecture);
            }
        }
        return result;
    }

    public ArrayList<Lecture> getTeacherScheduledLectures(Teacher teacher, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        ArrayList<Lecture> result = new ArrayList<>();
        ArrayList<String> subjectToFindSchedule = teacher.getSubject();

        List<Lecture> allScheduleLectures = university.getLectures();
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
