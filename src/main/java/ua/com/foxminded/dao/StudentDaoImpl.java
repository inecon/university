package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private JdbcExecutor<Student> jdbcExecutor;
    private static final Logger log = Logger.getLogger(StudentDaoImpl.class);

    public StudentDaoImpl(ConnectionFactory connectionFactory) throws SQLException {
        this.jdbcExecutor = new JdbcExecutor<Student>(connectionFactory);
    }

    @Override
    public List<Student> getAll() throws Exception {
        String sql = "select * from students";
        log.debug("Method getAll send sql request");
        return jdbcExecutor.execQuery(sql, result -> {
            List<Student> allStudents = new ArrayList<>();
            while (result.next()) {
                allStudents.add(new Student(result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("gender"),
                        result.getInt("age")));
            }
            return allStudents;
        });
    }

    @Override
    public Student getById(Integer id) throws Exception {
        String sql = "select * from students where id = ?";
        log.debug("Method getById send sql request with ID = " + id);
        return jdbcExecutor.execQuery(sql, result -> {
            result.next();
            return new Student(result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("gender"),
                    result.getInt("age"));
        }, id);
    }

    @Override
    public void create(Integer id, String name, String surName, String gender, Integer age) throws Exception {
        String sql = "insert into students (id, name, surname, gender, age) values (?,?,?,?,?)";
        log.debug("Method create send sql request with - ID = " + id + ", NAME = " + name + ", SURNAME = " + surName +
                ", GENDER = " + gender + ", age = " + age);
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void update( String name, String surName, String gender, Integer age, Integer id) throws Exception {
        String sql = "update students set name = ?, surname = ?, gender = ?, age = ? where id = ?";
        log.debug("Method update send sql request with NAME = " + name + ", SURNAME = " + surName +
                ", GENDER = " + gender + ", age = " + age +  ", ID = " + id);
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void deleteAll() throws Exception {
        String sql = "delete from students";
        log.debug("Method deleteAll send sql request");
        jdbcExecutor.execUpdate(sql);
    }
}
