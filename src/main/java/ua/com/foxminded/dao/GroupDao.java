package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.List;

public interface GroupDao {

    public List<Group> getAll() throws Exception;

    public Group getById(Integer id) throws Exception;

    public void create(Integer id, String title, String description) throws Exception;

    public void update(String title, String description, Integer id) throws Exception;

    public void deleteAll() throws Exception;
}
