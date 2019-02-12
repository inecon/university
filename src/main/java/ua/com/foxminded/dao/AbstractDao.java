package ua.com.foxminded.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractDao<T extends Serializable> {
    private Class<T> clazz;

    @Inject
    @Setter
    @Getter
    SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T getById(final Integer id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public void create(final T entity) {
        getCurrentSession().persist(entity);
    }

    public void update(final T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(final Integer id) {
        T entity = getById(id);
        getCurrentSession().delete(entity);
    }
}
