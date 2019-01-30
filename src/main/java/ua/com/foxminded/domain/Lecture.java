package ua.com.foxminded.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
public class Lecture implements Comparable<Lecture> {
    private Integer id;
    private LocalDateTime date;
    private String subject;
    private Teacher teacher;
    private Group group;
    private Integer classroom;

    public Lecture(Integer id, LocalDateTime date, String subject, Teacher teacher, Group group, Integer classroom) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.classroom = classroom;
    }

    public int compareTo(Lecture anotherLecture) {
        return new CompareToBuilder()
                .append(this.id, anotherLecture.id)
                .append(this.date, anotherLecture.date)
                .append(this.subject, anotherLecture.subject)
                .append(this.teacher, anotherLecture.teacher)
                .append(this.group, anotherLecture.group)
                .append(this.classroom, anotherLecture.classroom)
                .toComparison();
    }
}