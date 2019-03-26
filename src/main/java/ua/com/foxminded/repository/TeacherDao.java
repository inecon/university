package ua.com.foxminded.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.foxminded.domain.Teacher;

public interface TeacherDao extends PagingAndSortingRepository<Teacher, Integer> {}

