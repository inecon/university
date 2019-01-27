package ua.com.foxminded.dao;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.com.foxminded.domain.Teacher;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Log4j
public class TeacherDaoImpl implements TeacherDao {
    @Inject
    @Qualifier("teacher")
    private JdbcExecutor<Teacher> jdbcExecutor;

    public TeacherDaoImpl() {}

    @Override
    public List<Teacher> getAll() throws DaoException {
        Comparator<Teacher> byId = Comparator.comparing(Teacher::getId);
        final String sql = "select * from teachers";
        log.debug("Method getAll send sql request");
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                List<Teacher> allTeachers = new ArrayList<>();
                while (result.next()) {
                    allTeachers.add(new Teacher(result.getInt("id"),
                            result.getString("name"),
                            result.getString("surname"),
                            result.getString("gender"),
                            result.getInt("age")));
                }
                allTeachers.sort(byId);
                return allTeachers;
            });
        } catch (DaoException | SQLException e) {
            log.error("Exception in getAll method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public Teacher getById(Integer id) throws DaoException {
        final String sql = "select * from teachers where id = ?";
        log.debug("Method getById send sql request with ID = " + id);
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                result.next();
                return new Teacher(result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("gender"),
                        result.getInt("age"));
            }, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in getById method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Integer id, String name, String surName, String gender, Integer age) throws DaoException {
        final String sql = "insert into teachers (id, name, surname, gender, age) values (?,?,?,?,?)";
        log.debug("Method create send sql request with - ID = " + id + ", NAME = " + name + ", SURNAME = " + surName +
                ", GENDER = " + gender + ", age = " + age);
        try {
            jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
        } catch (DaoException | SQLException e) {
            log.error("Exception in create method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void update(String name, String surName, String gender, Integer age, Integer id) throws DaoException {
        final String sql = "update teachers set  name = ?, surname = ?, gender = ?, age = ? where id = ?";
        log.debug("Method update send sql request with NAME = " + name + ", SURNAME = " + surName +
                ", GENDER = " + gender + ", age = " + age + ", ID = " + id);
        try {
            jdbcExecutor.execUpdate(sql, name, surName, gender, age, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in update method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteAll() throws DaoException {
        final String sql = "delete from teachers";
        log.debug("Method deleteAll send sql request");
        try {
            jdbcExecutor.execUpdate(sql);
        } catch (DaoException | SQLException e) {
            log.error("Exception in deleteAll method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        final String sql = "delete from teachers where id = ?";
        log.debug("Method deleteById send sql request with id = " + id);
        try {
            jdbcExecutor.execUpdate(sql, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in deleteById method", e.getCause());
            throw new DaoException(e);
        }
    }
}
