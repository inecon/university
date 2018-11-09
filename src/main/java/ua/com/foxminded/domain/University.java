package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.foxminded.dao.ConnectionFactory;

import java.util.HashSet;
import java.util.Set;

public class University {
    private Set<Student> students = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();
    private Set<Group> groups = new HashSet<>();
    private Set<Lecture> lectures = new HashSet<>();
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public University() {}

    public University(Set<Student> students, Set<Teacher> teachers, Set<Group> groups,Set<Lecture> lectures, ConnectionFactory connectionFactory) {
        this.students = students;
        this.teachers = teachers;
        this.groups = groups;
        this.lectures = lectures;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.students + "\n");
        result.append(this.teachers + "\n");
        result.append(this.groups + "\n");
        result.append(this.lectures + "\n");
        return result.toString();
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Group> getGroups() {
        return groups ;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return null;//connectionFactory;
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
        University rhs = (University) obj;
        return new EqualsBuilder()
                .append(students, rhs.students)
                .append(teachers, rhs.teachers)
                .append(groups, rhs.groups)
                .append(lectures, rhs.lectures)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.students)
                .append(this.teachers)
                .append(this.groups)
                .append(this.lectures)
                .toHashCode();
    }


}
