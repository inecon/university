package ua.com.foxminded.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.foxminded.domain.Group;

public interface GroupDao extends PagingAndSortingRepository<Group, Integer> {}
