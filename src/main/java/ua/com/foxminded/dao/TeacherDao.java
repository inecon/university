package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.util.ArrayList;

public interface TeacherDao {
    public ArrayList<Teacher> getAll ();

    public Teacher getById(Integer id);

    public void add(Integer id, String name, String surName, String gender, Integer age);

    public void update(Integer id, String name, String surName, String gender, Integer age);

    public void deleteAll();
}

