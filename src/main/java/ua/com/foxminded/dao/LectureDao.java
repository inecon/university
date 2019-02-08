package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.util.List;

public interface LectureDao {

    List<Lecture> getAll() throws DaoException;

    Lecture getById(Integer id) throws DaoException;

    void create(Lecture lecture) throws DaoException;

    void update(Lecture lecture) throws DaoException;

    void deleteById(Integer id) throws DaoException;
}
