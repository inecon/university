package ua.com.foxminded.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.foxminded.domain.Student;

public interface StudentDao extends PagingAndSortingRepository<Student, Integer> {}
