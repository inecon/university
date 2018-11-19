package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    public List<Student> getAll() throws Exception;

    public Student getById(Integer id) throws Exception;

    public void create(Integer id, String name, String surName, String gender, Integer age) throws Exception;

    public void update( String name, String surName, String gender, Integer age, Integer id) throws Exception;

    public void deleteAll() throws Exception;
}
