package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.List;

public interface GroupDao {

    public List<Group> getAll() throws DaoException;

    public Group getById(Integer id) throws DaoException;

    public void create(Integer id, String title, String description) throws DaoException;

    public void update(String title, String description, Integer id) throws DaoException;

    public void deleteAll() throws DaoException;
}
