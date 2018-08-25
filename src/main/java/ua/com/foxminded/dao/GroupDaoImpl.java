package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private ConnectionFactory connectionFactory;

    public GroupDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Group> getAll() {
        List<Group> result = new ArrayList<>();
        String sql = "select * from groups";
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result.add(new Group(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description")));
            }

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
        return result;
    }

    @Override
    public Group getById(Integer id) {
        String sql = "select * from groups where id = ?";
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            resultSet = statement.getResultSet();
            resultSet.next();
            group = new Group(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"));

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
        return group;
    }

    @Override
    public void create(Integer id, String title, String description) {
        String sql = "insert into groups (id, title, description) values (?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.execute();

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
    }

    @Override
    public void update(Integer id, String title, String description) {
        String sql = "update groups set title = ?, description = ? where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, String.valueOf(id));
            statement.execute();
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
    }

    @Override
    public void deleteAll() {
        String sql = "delete from groups";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.execute();

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
    }
}
