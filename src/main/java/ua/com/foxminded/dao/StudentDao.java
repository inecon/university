package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Student;

import java.util.ArrayList;

public interface StudentDao {
    public ArrayList<Student> getAll();

    public Student getById(Integer id);

    public void create(Integer id, String name, String surName, String gender, Integer age);

    public void update(Integer id, String name, String surName, String gender, Integer age);

    public void deleteAll();
}
