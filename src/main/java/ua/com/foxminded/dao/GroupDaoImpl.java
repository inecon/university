package ua.com.foxminded.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.domain.Group;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

@Log4j
@NoArgsConstructor
@Transactional
public class GroupDaoImpl implements GroupDao {
    @Inject
    @Setter
    @Getter
    public SessionFactory sessionFactory;

    @Override
    public List<Group> getAll() {
        Session session = getSessionFactory().getCurrentSession();
        Comparator<Group> byId = Comparator.comparing(Group::getId);
        List<Group> allGroups = session.createQuery("FROM Group").list();
        allGroups.sort(byId);
        return allGroups;
    }

    @Override
    public Group getById(Integer id) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("FROM Group WHERE ID = :id ");
        query.setParameter("id", id);
        return (Group) query.uniqueResult();
    }

    @Override
    public void create(Group group) {
        Session session = getSessionFactory().getCurrentSession();
        session.save(group);
    }

    @Override
    public void update(Group group) {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("UPDATE Group SET title = :title, description = :description" +
                " WHERE ID = :id");
        query.setParameter("title", group.getTitle());
        query.setParameter("description", group.getDescription());
        query.setParameter("id", group.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery("DELETE Group WHERE ID = :id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
