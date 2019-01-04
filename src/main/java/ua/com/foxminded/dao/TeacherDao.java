package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.util.List;

public interface TeacherDao {
    public List<Teacher> getAll() throws DaoException;

    public Teacher getById(Integer id) throws DaoException;

    public void create(Integer id, String name, String surName, String gender, Integer age) throws DaoException;

    public void update(String name, String surName, String gender, Integer age, Integer id) throws DaoException;

    public void deleteAll() throws DaoException;

    public void deleteById(Integer id);
}

