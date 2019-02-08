package ua.com.foxminded.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Lecture;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

@Log4j
@NoArgsConstructor
@Transactional
public class LectureDaoImpl implements LectureDao {

    @Inject
    @Setter
    @Getter
    public SessionFactory sessionFactory;

    @Override
    public List<Lecture> getAll() {
        Session session = getSessionFactory().getCurrentSession();
        Comparator<Lecture> byId = Comparator.comparing(Lecture::getId);
        List<Lecture> allLectures = session.createQuery("FROM Lecture").list();
        allLectures.sort(byId);
        return allLectures;
    }

    @Override
    public Lecture getById(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("FROM Lecture WHERE ID = :id ");
        query.setParameter("id", id);
        return (Lecture) query.uniqueResult();
    }

    @Override
    public void create(Lecture lecture) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(lecture);
    }

    @Override
    public void update(Lecture lecture) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("UPDATE Lecture SET date = :date, subject = :subject," +
                "teacher_id = :teacher_id, group_id = :group_id , classroom = :classroom" +
                " WHERE ID = :id");
        query.setParameter("date", lecture.getDate());
        query.setParameter("subject", lecture.getSubject());
        query.setParameter("teacher_id", lecture.getTeacher().getId());
        query.setParameter("group_id", lecture.getGroup().getId());
        query.setParameter("classroom", lecture.getClassroom());
        query.setParameter("id", lecture.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("DELETE Lecture WHERE ID = :ID ");
        query.setParameter("ID", id);
        query.executeUpdate();
    }
}
