package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAll();

    Student getById(Integer id);

    void create(Student student);

    void update(Student student);

    void deleteById(Integer id);
}
