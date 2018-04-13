package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

public class Lecture implements Comparable{

    private LocalDateTime date;
    private String subject;
    private Teacher teacher;
    private Group group;
    private Integer classroom;

    public Lecture() {

    }

    public Lecture(LocalDateTime date, String subject, Teacher teacher, Group group, Integer classrom) {
        this.date = date;
        this.subject = subject;
        this.teacher = teacher;
        this.group = group;
        this.classroom = classrom;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
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
                .append(date, rhs.getDate())
                .append(subject, rhs.getSubject())
                .append(teacher, rhs.getTeacher())
                .append(group, rhs.getGroup())
                .append(classroom, rhs.getClassroom())
                .isEquals();
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

////////////////////////////TO DO/////////////////////////
    public int compareTo(Object o) {
        return 0;
    }
}
