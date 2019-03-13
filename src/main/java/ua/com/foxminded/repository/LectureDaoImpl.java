package ua.com.foxminded.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Lecture;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Transactional
public class LectureDaoImpl extends AbstractDao <Lecture> implements LectureDao {
    public LectureDaoImpl(){
        setClazz(Lecture.class);
    }

    @Override
    public List<Lecture> getAll() {
        List<Lecture> list = super.getAll();
        list.sort(Comparator.comparing(Lecture::getId));
        return list;
    }
}
