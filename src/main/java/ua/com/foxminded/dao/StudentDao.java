package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> getAll() throws DaoException;

    public Student getById(Integer id) throws DaoException;

    public void create(Integer id, String name, String surName, String gender, Integer age) throws DaoException;

    public void update( String name, String surName, String gender, Integer age, Integer id) throws DaoException;

    public void deleteAll() throws DaoException;
}
