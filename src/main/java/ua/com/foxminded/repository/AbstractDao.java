package ua.com.foxminded.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractDao<T extends Serializable> {
    private Class<T> clazz;


    @Setter
    @Getter
    @PersistenceContext
    protected EntityManager entityManager;

    public void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T getById(final Integer id) {
        return (T) entityManager.find(clazz, id);
    }

    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(final T entity) {
        entityManager.persist(entity);
    }

    public void update(final T entity) {
        entityManager.merge(entity);
    }

    public void delete(final Integer id) {
        T entity = getById(id);
        entityManager.remove(entity);
    }
}
