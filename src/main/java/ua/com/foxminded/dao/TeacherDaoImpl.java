package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherDaoImpl implements TeacherDao {
    private DaoFactory daoFactory;

    public TeacherDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Teacher> getAll() {
        return null;
    }

    @Override
    public Teacher getById(Integer id) {
        String sql = "select * from teachers where id = ?";
        Teacher teacher = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            resultSet = statement.getResultSet();
            resultSet.next();
            teacher = new Teacher(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surName"),
                    resultSet.getString("gender"),
                    resultSet.getInt("age"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teacher;
    }

    @Override
    public void add(Integer id, String name, String surName, String gender, Integer age) {

    }

    @Override
    public void update(Integer id, String name, String surName, String gender, Integer age) {

    }

    @Override
    public void deleteAll() {

    }
}
