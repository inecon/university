package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureDao {

    public List<Lecture> getAllByDate(LocalDateTime date);

    public void addLecture(Lecture lecture);

    public void update(LocalDateTime date, String subject);

    public void deleteAll(LocalDateTime date, String subject);
}
