package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private Executor<Group> executor;
    private static final Logger log = Logger.getLogger(GroupDaoImpl.class);

    public GroupDaoImpl(ConnectionFactory connectionFactory) {
        this.executor = new Executor<Group>(connectionFactory);
        log.info("Вызов конструктора");
    }

    @Override
    public List<Group> getAll() throws SQLException {
        String sql = "select * from groups";
        return executor.execQuery(sql, result -> {
            List<Group> allGroups = new ArrayList<>();
            while (result.next()) {
                allGroups.add(new Group(result.getInt("id"),
                        result.getString("title"),
                        result.getString("description")));
            }
            log.info("Метод отработал нормально");
            return allGroups;
        });
    }

    @Override
    public Group getById(Integer id) throws SQLException {
        String sql = "select * from groups where id = ?";
        return executor.execQuery(sql, result -> {
            result.next();
            return new Group(result.getInt("id"),
                    result.getString("title"),
                    result.getString("description"));
        });
    }

    @Override
    public void create(Integer id, String title, String description) throws SQLException {
        String sql = "insert into groups (id, title, description) values (?,?,?)";
        executor.execUpdate(sql, id, title, description);
    }

    @Override
    public void update(Integer id, String title, String description) throws SQLException {
        String sql = "update groups set title = ?, description = ? where id = ?";
        executor.execUpdate(sql, id, title, description);
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "delete from groups";
        executor.execUpdate(sql);
    }
}
