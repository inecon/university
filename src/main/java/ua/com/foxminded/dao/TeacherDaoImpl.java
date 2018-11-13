package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    private JdbcExecutor<Teacher> jdbcExecutor;
    private static final Logger log = Logger.getLogger(TeacherDaoImpl.class);

    public TeacherDaoImpl(ConnectionFactory connectionFactory) {
        this.jdbcExecutor = new JdbcExecutor<Teacher>(connectionFactory);
    }

    @Override
    public List<Teacher> getAll() {
        String sql = "select * from teachers";
        log.info("Метод getAll отправил запрос в БД");
        return jdbcExecutor.execQuery(sql, result -> {
            List<Teacher> allTeachers = new ArrayList<>();
            while (result.next()) {
                allTeachers.add(new Teacher(result.getInt("id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("gender"),
                        result.getInt("age")));
            }
            return allTeachers;
        });
    }

    @Override
    public Teacher getById(Integer id) {
        String sql = "select * from teachers where id = ?";
        log.info("Метод getById отправил запрос в БД");
        return jdbcExecutor.execQuery(sql, result -> {
            result.next();
            return new Teacher(result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("gender"),
                    result.getInt("age"));
        }, id);
    }

    @Override
    public void create(Integer id, String name, String surName, String gender, Integer age) {
        String sql = "insert into teachers (id, name, surname, gender, age) values (?,?,?,?,?)";
        log.info("Метод create отправил запрос в БД");
            jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void update(Integer id, String name, String surName, String gender, Integer age) {
        String sql = "update teachers set  id = ?, name = ?, surname = ?, gender = ?, age = ? where id = ?";
        log.info("Метод update отправил запрос в БД");
            jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void deleteAll() {
        String sql = "delete from teachers";
        log.info("Метод deleteAll отправил запрос в БД");
        jdbcExecutor.execUpdate(sql);
    }
}
