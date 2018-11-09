package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupDao {

    public List<Group> getAll() throws SQLException;

    public Group getById(Integer id) throws SQLException;

    public void create(Integer id, String title, String description) throws Exception;

    public void update(Integer id, String title, String description) throws SQLException;

    public void deleteAll() throws SQLException;
}
