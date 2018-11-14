package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    public List<Student> getAll() throws SQLException;

    public Student getById(Integer id) throws SQLException;

    public void create(Integer id, String name, String surName, String gender, Integer age) throws SQLException;

    public void update( String name, String surName, String gender, Integer age, Integer id) throws SQLException;

    public void deleteAll() throws SQLException;
}
