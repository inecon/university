package ua.com.foxminded.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "lectures")
public class Lecture implements Comparable<Lecture>, Serializable {
    @Id
    private Integer id;
    @Column(name = "date")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime date;
    @Column
    private String subject;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @Column
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