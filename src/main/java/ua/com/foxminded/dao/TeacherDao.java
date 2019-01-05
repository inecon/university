package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getAll() throws DaoException;

    Teacher getById(Integer id) throws DaoException;

    void create(Integer id, String name, String surName, String gender, Integer age) throws DaoException;

    void update(String name, String surName, String gender, Integer age, Integer id) throws DaoException;

    void deleteAll() throws DaoException;

    void deleteById(Integer id);
}

