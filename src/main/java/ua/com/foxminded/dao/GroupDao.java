package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.util.List;

public interface GroupDao {

    List<Group> getAll();

    Group getById(Integer id);

    void create(Group group);

    void update(Group group);

    void deleteById(Integer id);
}
