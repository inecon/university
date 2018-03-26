package ua.com.foxminded.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private University university;

   public Set<Lecture> getScheduledLectures (Student student, LocalDateTime startPeriod, LocalDateTime endPeriod){
       Set<Lecture> result = new TreeSet<Lecture>();

       return result;
   }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
