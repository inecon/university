package ua.com.foxminded.repository;

import ua.com.foxminded.domain.Lecture;

import java.util.List;

public interface LectureDao {

    List<Lecture> getAll();

    Lecture getById(final Integer id);

    void create(Lecture lecture);

    void update(Lecture lecture);

    void delete(final Integer id);
}
