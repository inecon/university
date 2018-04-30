package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    public int compareTo(Object another) {
        if (another instanceof University) {
            University anotherUniversity = (University) another;
            return new CompareToBuilder()
                    .append(this.students, anotherUniversity.students)
                    .append(this.teachers, anotherUniversity.teachers)
                    .append(this.groups, anotherUniversity.groups)
                    .append(this.lectures, anotherUniversity.lectures)
                    .toComparison();
        } else {
            return -1;
        }
    }
}
