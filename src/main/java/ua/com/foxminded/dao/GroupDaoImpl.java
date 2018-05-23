package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.sql.*;
import java.util.ArrayList;

public class GroupDaoImpl implements GroupDao {
    private DaoFactory daoFactory;

    public GroupDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Group> getAll() {
        ArrayList<Group> result = new ArrayList<>();
        String sql = "select * from groups";
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result.add(new Group(resultSet.getString("title"), resultSet.getString("description")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void addGroup(String title, String description) {
        String sql = "insert into groups (title, description) values (?,?)";
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            group = new Group(resultSet.getString("title"), resultSet.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Group group) {

    }

    @Override
    public void deleteByTitle(String title) {

    }
}
