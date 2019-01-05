package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

public class Lecture implements Comparable<Lecture> {
    private Integer id;
    private LocalDateTime date;
    private String subject;
    private Teacher teacher;
    private Group group;
    private Integer classroom;

    public Lecture() {
    }

    public Lecture(Integer id, LocalDateTime date, String subject, Teacher teacher, Group group, Integer classroom) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.classroom = classroom;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.date)
                .append(this.subject)
                .append(this.teacher)
                .append(this.group)
                .append(this.classroom)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Lecture rhs = (Lecture) obj;
        return new EqualsBuilder()
                .append(id, rhs.getId())
                .append(date, rhs.getDate())
                .append(subject, rhs.getSubject())
                .append(teacher, rhs.getTeacher())
                .append(group, rhs.getGroup())
                .append(classroom, rhs.getClassroom())
                .isEquals();
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.id + "\n");
        result.append(this.date + "\n");
        result.append(this.subject + "\n");
        result.append(this.teacher + "\n");
        result.append(this.group + "\n");
        result.append(this.classroom + "\n");
        return result.toString();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }
}