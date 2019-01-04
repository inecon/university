package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.util.List;

public interface LectureDao {

    public List<Lecture> getAll() throws DaoException;

    public void create(Lecture lecture) throws DaoException;

    public void update(Lecture lecture) throws DaoException;

    public void deleteAll() throws DaoException;

    public void deleteById(Integer id) throws DaoException;
}
