package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.foxminded.dao.ConnectionFactory;
import ua.com.foxminded.dao.LectureDaoImpl;
import ua.com.foxminded.dao.StudentDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Group> groups = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public University() {}

    public University(List<Student> students, List<Teacher> teachers, List<Group> groups, List<Lecture> lectures, ConnectionFactory connectionFactory) {
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

    public List<Student> getStudents() {
        return new StudentDaoImpl(connectionFactory).getAll();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return null;///new TeacherDaoImpl(connectionFactory).getAll();
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Group> getGroups() {
        return null ;//new GroupDaoImpl(connectionFactory).getAll();
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Lecture> getLectures() {
        return new LectureDaoImpl(connectionFactory).getAll();
    }

    public void setLectures(List<Lecture> lectures) {
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
