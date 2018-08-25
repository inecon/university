package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.List;

public interface GroupDao {

    public List<Group> getAll();

    public Group getById(Integer id);

    public void create(Integer id, String title, String description);

    public void update(Integer id, String title, String description);

    public void deleteAll();
}
