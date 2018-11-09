package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    private JdbcExecutor<Teacher> jdbcExecutor;

    public TeacherDaoImpl(ConnectionFactory connectionFactory) {
        this.jdbcExecutor = new JdbcExecutor<Teacher>(connectionFactory);
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        String sql = "select * from teachers";
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
    public Teacher getById(Integer id) throws SQLException {
        String sql = "select * from teachers where id = ?";
        return jdbcExecutor.execQuery(sql, result -> {
            result.next();
            return new Teacher(result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("gender"),
                    result.getInt("age"));
        });
    }

    @Override
    public void create(Integer id, String name, String surName, String gender, Integer age) throws SQLException {
        String sql = "insert into teachers (id, name, surname, gender, age) values (?,?,?,?,?)";
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void update(Integer id, String name, String surName, String gender, Integer age) throws SQLException {
        String sql = "update teachers set  id = ?, name = ?, surname = ?, gender = ?, age = ? where id = ?";
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "delete from teachers";
        jdbcExecutor.execUpdate(sql);
    }
}
