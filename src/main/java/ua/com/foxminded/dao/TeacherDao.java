package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.util.List;

public interface TeacherDao {
    public List<Teacher> getAll();

    public Teacher getById(Integer id);

    public void create(Integer id, String name, String surName, String gender, Integer age);

    public void update(Integer id, String name, String surName, String gender, Integer age);

    public void deleteAll();
}

