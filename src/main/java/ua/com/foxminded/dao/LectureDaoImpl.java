package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LectureDaoImpl implements LectureDao {
    private ConnectionFactory connectionFactory;


    public LectureDaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Lecture> getAll() {
        List<Lecture> result = new ArrayList<>();
        String sql = "select * from lectures";
        GroupDao groupDao = new GroupDaoImpl(connectionFactory);
        TeacherDao teacherDao = new TeacherDaoImpl(connectionFactory);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new ConnectionFactory().getConnection();
            statement = connection.prepareStatement(sql);
            statement.execute();
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result.add(new Lecture((LocalDateTime.parse(resultSet.getString("date"))),
                        resultSet.getString("subject"),
                        teacherDao.getById(resultSet.getInt("teacher_id")),
                        groupDao.getById(resultSet.getInt("group_id")),
                        resultSet.getInt("classroom")));
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
    public void create(Lecture lecture) {
        String sql = "insert into lectures (date, subject, teacher_id, group_id, classroom) values (?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, lecture.getDate().toString());
            statement.setString(2, lecture.getSubject());
            statement.setInt(3, lecture.getTeacher().getId());
            statement.setInt(4, lecture.getGroup().getId());
            statement.setInt(5, lecture.getClassroom());
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
    public void update(Lecture lecture) {
        String sql = "update lectures set  date = ?, subject = ?, teacher_id = ?, group_id = ?, classroom = ? where date = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, lecture.getDate().toString());
            statement.setString(2, lecture.getSubject());
            statement.setInt(3, lecture.getTeacher().getId());
            statement.setInt(4, lecture.getGroup().getId());
            statement.setInt(5, lecture.getClassroom());
            statement.setString(6, lecture.getDate().toString());
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
        String sql = "delete from lectures";
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
