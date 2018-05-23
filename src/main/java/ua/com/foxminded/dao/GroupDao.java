package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.ArrayList;

public interface GroupDao {

    public ArrayList<Group> getAll ();

    public void addGroup(String title, String description);

    public void update(Group group);

    public void deleteByTitle(String title);
}
