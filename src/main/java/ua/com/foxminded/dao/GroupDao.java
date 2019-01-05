package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.List;

public interface GroupDao {

    List<Group> getAll() throws DaoException;

    Group getById(Integer id) throws DaoException;

    void create(Integer id, String title, String description) throws DaoException;

    void update(String title, String description, Integer id) throws DaoException;

    void deleteAll() throws DaoException;

    void deleteById(Integer id) throws DaoException;
}
