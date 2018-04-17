package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;
import java.util.TreeSet;

public class University {

    private Set<Student> students = new TreeSet<Student>();
    private Set<Teacher> teachers = new TreeSet<Teacher>();
    private Set<Group> groups = new TreeSet<Group>();
    private Set<Lecture> lectures = new TreeSet<Lecture>();

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Set<Group> getGroups() {
        return groups;
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

    public University() {

    }
}
