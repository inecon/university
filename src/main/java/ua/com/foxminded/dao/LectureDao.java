package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.sql.SQLException;
import java.util.List;

public interface LectureDao {

    public List<Lecture> getAll() throws SQLException;

    public void create(Lecture lecture) throws SQLException;

    public void update(Lecture lecture) throws SQLException;

    public void deleteAll() throws SQLException;
}
