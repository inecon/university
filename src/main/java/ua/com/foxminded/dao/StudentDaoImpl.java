package ua.com.foxminded.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Student;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

@Log4j
@NoArgsConstructor
@Transactional
public class StudentDaoImpl implements StudentDao {
    @Inject
    @Setter
    @Getter
    public SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
        Session session = getSessionFactory().getCurrentSession();
        Comparator<Student> byId = Comparator.comparing(Student::getId);
        List<Student> allStudents = session.createQuery("FROM Student").list();
        allStudents.sort(byId);
        return allStudents;
    }

    @Override
    public Student getById(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("FROM Student WHERE ID = :id ");
        query.setParameter("id", id);
        return (Student) query.uniqueResult();
    }

    @Override
    public void create(Student student) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(student);
    }

    @Override
    public void update(Student student) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("UPDATE Student SET name = :name, surName = :surName," +
                "age = :age, gender = :gender , group_id = :group_id" +
                " WHERE ID = :id");
        query.setParameter("name", student.getName());
        query.setParameter("surName", student.getSurName());
        query.setParameter("age", student.getAge());
        query.setParameter("gender", student.getGender());
        query.setParameter("group_id", student.getGroup().getId());
        query.setParameter("id", student.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("DELETE Student WHERE ID = :ID ");
        query.setParameter("ID", id);
        query.executeUpdate();
    }
}
