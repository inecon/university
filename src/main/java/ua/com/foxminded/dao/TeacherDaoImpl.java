package ua.com.foxminded.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Teacher;

import java.util.Comparator;
import java.util.List;

@Transactional
@Log4j
@NoArgsConstructor
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    @Setter
    @Getter
    public SessionFactory sessionFactory;

    @Override
    public List<Teacher> getAll() {
        Session session = getSessionFactory().getCurrentSession();
        Comparator<Teacher> byId = Comparator.comparing(Teacher::getId);
        List<Teacher> allTeachers = session.createQuery("FROM Teacher").list();
        allTeachers.sort(byId);
        return allTeachers;
    }

    @Override
    public Teacher getById(Integer id) throws DaoException {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("FROM Teacher WHERE ID = :id ");
        query.setParameter("id", id);
        return (Teacher) query.uniqueResult();
    }

    @Override
    public void create(Teacher teacher) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("UPDATE Teacher SET name = :name, surName = :surName," +
                "age = :age, gender = :gender , subject = :subject" +
                " WHERE ID = :id");
        query.setParameter("name", teacher.getName());
        query.setParameter("surName", teacher.getSurName());
        query.setParameter("age", teacher.getAge());
        query.setParameter("gender", teacher.getGender());
        query.setParameter("subject", teacher.getSubject());
        query.setParameter("id", teacher.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("DELETE Teacher WHERE ID = :ID ");
        query.setParameter("ID", id);
        query.executeUpdate();
    }
}
