package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.foxminded.dao.*;
import ua.com.foxminded.di.Context;

import java.util.ArrayList;

public class University {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Lecture> lectures = new ArrayList<>();
    private Context context = new Context();

    public University() {}

    public University(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Group> groups, ArrayList<Lecture> lectures, Context context) {
        this.students = students;
        this.teachers = teachers;
        this.groups = groups;
        this.lectures = lectures;
        this.context = context;
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

    public ArrayList<Student> getStudents() {
        return null;//new StudentDaoImpl(connectionFactory).getAll();
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Teacher> getTeachers() {
        return null;///new TeacherDaoImpl(connectionFactory).getAll();
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Group> getGroups() {
        return null ;//new GroupDaoImpl(connectionFactory).getAll();
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Lecture> getLectures() {
        return new LectureDaoImpl(context).getAll();
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        //this.connectionFactory = connectionFactory;
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
