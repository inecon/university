package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {
    public List<Teacher> getAll() throws SQLException;

    public Teacher getById(Integer id) throws SQLException;

    public void create(Integer id, String name, String surName, String gender, Integer age) throws SQLException;

    public void update(Integer id, String name, String surName, String gender, Integer age) throws SQLException;

    public void deleteAll() throws Exception;
}

