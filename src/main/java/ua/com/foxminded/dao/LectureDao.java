package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.util.List;

public interface LectureDao {

    public List<Lecture> getAll();

    public void create(Lecture lecture);

    public void update(Lecture lecture);

    public void deleteAll();
}
