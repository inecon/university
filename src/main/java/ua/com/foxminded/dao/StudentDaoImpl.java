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
    public List<Student> getAll() {
        String sql = "select * from students";
        log.info("Метод getAll отправил запрос в БД");
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
    public Student getById(Integer id) {
        String sql = "select * from students where id = ?";
        log.info("Метод getById отправил запрос в БД");
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
    public void create(Integer id, String name, String surName, String gender, Integer age) {
        String sql = "insert into students (id, name, surname, gender, age) values (?,?,?,?,?)";
        log.info("Метод create отправил запрос в БД");
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void update(Integer id, String name, String surName, String gender, Integer age) {
        String sql = "update students set  id = ?, name = ?, surname = ?, gender = ?, age = ? where id = ?";
        log.info("Метод update отправил запрос в БД");
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void deleteAll() {
        String sql = "delete from students";
        log.info("Метод deleteAll отправил запрос в БД");
        jdbcExecutor.execUpdate(sql);
    }
}
