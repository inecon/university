package ua.com.foxminded.repository;

import lombok.extern.log4j.Log4j;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Teacher;

import java.util.Comparator;
import java.util.List;

@Transactional
@Log4j
public class TeacherDaoImpl extends AbstractDao<Teacher> implements TeacherDao {
    public TeacherDaoImpl() {
        setClazz(Teacher.class);
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> list = super.getAll();
        list.sort(Comparator.comparing(Teacher::getId));
        return list;
    }
}
