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
    }

    @Override
    public List<Group> getAll() {
        String sql = "select * from groups";
        log.debug("Method getAll getById send sql");
        return jdbcExecutor.execQuery(sql, result -> {
            List<Group> allGroups = new ArrayList<>();
            while (result.next()) {
                allGroups.add(new Group(result.getInt("id"),
                        result.getString("title"),
                        result.getString("description")));
            }
            return allGroups;
        });
    }

    @Override
    public Group getById(Integer id) {
        String sql = "select * from groups where id = ?";
        log.debug("Method getById send sql");
        return jdbcExecutor.execQuery(sql, result -> {
            result.next();
            return new Group(result.getInt("id"),
                    result.getString("title"),
                    result.getString("description"));
        }, id);
    }

    @Override
    public void create(Integer id, String title, String description) {
        String sql = "insert into groups (id, title, description) values (?,?,?)";
        log.debug("Method CREATE send sql - ID = " + id + ", TITLE = " + title + ", DESCRIPTION = " + description);
        jdbcExecutor.execUpdate(sql, id, title, description);
    }

    @Override
    public void update(String title, String description, Integer id) {
        String sql = "update groups set title = ?, description = ? where id = ?";
        log.debug("Method update send sql - ID = " + id + ", TITLE = " + title + ", DESCRIPTION = " + description);
        jdbcExecutor.execUpdate(sql, title, description, id);
    }

    @Override
    public void deleteAll() {
        String sql = "delete from groups";
        log.debug("Method update send sql");
        jdbcExecutor.execUpdate(sql);
    }
}
