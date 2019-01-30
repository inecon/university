package ua.com.foxminded.dao;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import ua.com.foxminded.domain.Student;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Log4j
@NoArgsConstructor
public class StudentDaoImpl implements StudentDao {
    @Inject
    private JdbcExecutor<?> jdbcExecutor;

    @Override
    public List<Student> getAll() throws DaoException {
        Comparator<Student> byId = Comparator.comparing(Student::getId);
        final String sql = "select * from students";
        log.debug("Method getAll send sql request");
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                List<Student> allStudents = new ArrayList<>();
                while (result.next()) {
                    allStudents.add(new Student(result.getInt("id"),
                            result.getString("name"),
                            result.getString("surname"),
                            result.getString("gender"),
                            result.getInt("age")));
                }
                allStudents.sort(byId);
                return allStudents;
            });
        } catch (DaoException | SQLException e) {
            log.error("Exception in getAll method " + e);
            throw new DaoException(e);
        }
    }

    @Override
    public Student getById(Integer id) throws DaoException {
        final String sql = "select * from students where id = ?";
        log.debug("Method getById send sql request with ID = " + id);
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                result.next();
                return new Student(result.getInt("id"),
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
        final String sql = "insert into students (id, name, surname, gender, age) values (?,?,?,?,?)";
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
        final String sql = "update students set name = ?, surname = ?, gender = ?, age = ? where id = ?";
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
        final String sql = "delete from students";
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
        final String sql = "delete from students where id = ?";
        log.debug("Method deleteById send sql request with id = " + id);
        try {
            jdbcExecutor.execUpdate(sql, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in deleteById method", e.getCause());
            throw new DaoException(e);
        }
    }
}
