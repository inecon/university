package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getAll();

    Teacher getById(final Integer id);

    void create(Teacher teacher);

    void update(Teacher teacher);

    void delete(final Integer id);
}

