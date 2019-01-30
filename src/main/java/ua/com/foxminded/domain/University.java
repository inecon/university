package ua.com.foxminded.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.foxminded.dao.ConnectionFactory;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class University {
    private Set<Student> students = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();
    private Set<Group> groups = new HashSet<>();
    private Set<Lecture> lectures = new HashSet<>();
    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public University(Set<Student> students, Set<Teacher> teachers, Set<Group> groups, Set<Lecture> lectures, ConnectionFactory connectionFactory) {
        this.students = students;
        this.teachers = teachers;
        this.groups = groups;
        this.lectures = lectures;
        this.connectionFactory = connectionFactory;
    }
}
