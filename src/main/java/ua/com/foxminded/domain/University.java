package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Set;
import java.util.TreeSet;

public class University implements Comparable{
    private Set<Student> students = new TreeSet<Student>();
    private Set<Teacher> teachers = new TreeSet<Teacher>();
    private Set<Group> groups = new TreeSet<Group>();
    private Set<Lecture> lectures = new TreeSet<Lecture>();

    public University() {}

    public University(Set<Student> students, Set<Teacher> teachers, Set<Group> groups, Set<Lecture> lectures) {
        this.students = students;
        this.teachers = teachers;
        this.groups = groups;
        this.lectures = lectures;
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

    public int compareTo(Object o) {
        if (o instanceof University) {
            //University university = (University) o;
            return new CompareToBuilder()
                    .append(this.students, ((University) o).students)
                    .append(this.teachers, ((University) o).teachers)
                    .append(this.groups, ((University) o).groups)
                    .append(this.lectures, ((University) o).lectures)
                    .toComparison();
        } else
            return -1;
    }
}
