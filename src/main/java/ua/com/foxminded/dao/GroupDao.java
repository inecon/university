package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.ArrayList;

public interface GroupDao {

    public ArrayList<Group> getAll ();

    public Group getById(Integer id);

    public void addGroup(Integer id, String title, String description);

    public void update(Integer id, String title, String description);

    public void deleteAll();
}
