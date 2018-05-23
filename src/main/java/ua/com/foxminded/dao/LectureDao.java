package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.time.LocalDateTime;

public interface LectureDao {

    public Lecture getByDateANDSubject(LocalDateTime date, String subject);

    public void addLecture(Lecture lecture);

    public void deleteByDateANDSubject(LocalDateTime date, String subject);
}
