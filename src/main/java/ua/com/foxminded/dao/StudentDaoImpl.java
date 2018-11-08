package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private JdbcExecutor<Student> jdbcExecutor;

    public StudentDaoImpl(ConnectionFactory connectionFactory) throws SQLException {
        this.jdbcExecutor = new JdbcExecutor<Student>(connectionFactory);
    }

    @Override
    public List<Student> getAll() throws SQLException {
        String sql = "select * from students";
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
    public Student getById(Integer id) throws SQLException {
        String sql = "select * from students where id = ?";
        return jdbcExecutor.execQuery(sql, result -> {
            result.next();
            return new Student(result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("gender"),
                    result.getInt("age"));
        });
    }

    @Override
    public void create(Integer id, String name, String surName, String gender, Integer age) throws SQLException {
        String sql = "insert into students (id, name, surname, gender, age) values (?,?,?,?,?)";
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void update(Integer id, String name, String surName, String gender, Integer age) throws SQLException {
        String sql = "update students set  id = ?, name = ?, surname = ?, gender = ?, age = ? where id = ?";
        jdbcExecutor.execUpdate(sql, id, name, surName, gender, age);
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "delete from students";
        jdbcExecutor.execUpdate(sql);
    }
}
