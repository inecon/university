package ua.com.foxminded.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.foxminded.domain.Lecture;

public interface LectureDao  extends PagingAndSortingRepository<Lecture, Integer> {}