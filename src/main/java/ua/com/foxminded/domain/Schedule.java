package ua.com.foxminded.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private University university;

    public Set<Lecture> getScheduledLectures(Student student, LocalDateTime startPeriod, LocalDateTime endPeriod) {
        Set<Lecture> result = new TreeSet<Lecture>();
        Group groupToFindSchedule = student.getGroup();

        Set<Lecture> allScheduleLectures = university.getLectures();
        for (Lecture lecture : allScheduleLectures) {
            if (lecture.getGroup().equals(groupToFindSchedule)) {
                result.add(lecture);
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
