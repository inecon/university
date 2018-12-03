package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.sql.SQLException;
import java.util.List;

public interface LectureDao {

    public List<Lecture> getAll() throws Exception;

    public void create(Lecture lecture) throws Exception;

    public void update(Lecture lecture) throws Exception;

    public void deleteAll() throws Exception;
}
