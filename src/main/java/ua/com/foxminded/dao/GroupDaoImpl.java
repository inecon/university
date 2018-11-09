package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private JdbcExecutor<Group> jdbcExecutor;
    private static final Logger log = Logger.getLogger(GroupDaoImpl.class);

    public GroupDaoImpl(ConnectionFactory connectionFactory) {
        this.jdbcExecutor = new JdbcExecutor<Group>(connectionFactory);
        log.info("Вызов конструктора");
    }

    @Override
    public List<Group> getAll() {
        String sql = "select * from groups";
        return jdbcExecutor.execQuery(sql, result -> {
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
    public Group getById(Integer id) {
        String sql = "select * from groups where id = ?";
        return jdbcExecutor.execQuery(sql, result -> {
            result.next();
            return new Group(result.getInt("id"),
                    result.getString("title"),
                    result.getString("description"));
        });
    }

    @Override
    public void create(Integer id, String title, String description) {
        String sql = "insert into groups (id, title, description) values (?,?,?)";
        jdbcExecutor.execUpdate(sql, id, title, description);
    }

    @Override
    public void update(Integer id, String title, String description) {
        String sql = "update groups set title = ?, description = ? where id = ?";
        jdbcExecutor.execUpdate(sql, id, title, description);
    }

    @Override
    public void deleteAll() {
        String sql = "delete from groups";
        jdbcExecutor.execUpdate(sql);
    }
}
