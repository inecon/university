package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherDao {
    public List<Teacher> getAll() throws Exception;

    public Teacher getById(Integer id) throws Exception;

    public void create(Integer id, String name, String surName, String gender, Integer age) throws Exception;

    public void update(String name, String surName, String gender, Integer age, Integer id) throws Exception;

    public void deleteAll() throws Exception;
}

