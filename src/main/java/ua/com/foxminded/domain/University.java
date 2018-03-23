package ua.com.foxminded.domain;

import java.util.Set;

public class University extends Model{
    private static final long serialVersionUID = 585873164846446845L;

    private Set<Student> students;
    private Set<Teacher> teachers;
    private Set<Group> groups;
    private Set<Lecture> lectures;

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

    public University(){

    }
}
