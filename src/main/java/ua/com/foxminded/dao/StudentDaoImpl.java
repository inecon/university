package ua.com.foxminded.dao;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Student;

import java.util.Comparator;
import java.util.List;

@Log4j
@Transactional
@Service
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {
    public StudentDaoImpl(){
        setClazz(Student.class);
    }
    @Override
    public List<Student> getAll(){
        List<Student> list = super.getAll();
        list.sort(Comparator.comparing(Student::getId));
        return list;
    }
}
