package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {
    private JdbcExecutor<Group> jdbcExecutor;
    private static final Logger log = Logger.getLogger(GroupDaoImpl.class);

    public GroupDaoImpl(ConnectionFactory connectionFactory) {
        this.jdbcExecutor = new JdbcExecutor<Group>(connectionFactory);
    }

    @Override
    public List<Group> getAll() throws DaoException {
        String sql = "select * from groups";
        log.debug("Method getAll send sql request");
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                List<Group> allGroups = new ArrayList<>();
                while (result.next()) {
                    allGroups.add(new Group(result.getInt("id"),
                            result.getString("title"),
                            result.getString("description")));
                }
                return allGroups;
            });
        } catch (DaoException | SQLException e) {
            log.error("Exception in getAll method", e.getCause());
            throw new DaoException((RuntimeException) e);
        }
    }

    @Override
    public Group getById(Integer id) throws DaoException {
        String sql = "select * from groups where id = ?";
        log.debug("Method getById send sql request with ID = " + id);
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                result.next();
                return new Group(result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"));
            }, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in getById method", e.getCause());
            throw new DaoException((RuntimeException) e);
        }
    }

    @Override
    public void create(Integer id, String title, String description) throws DaoException {
        String sql = "insert into groups (id, title, description) values (?,?,?)";
        log.debug("Method CREATE send sql request - ID = " + id + ", TITLE = " + title + ", DESCRIPTION = " + description);
        try {
            jdbcExecutor.execUpdate(sql, id, title, description);
        } catch (Exception e) {
            log.error("Exception in create method", e.getCause());
        }
    }

    @Override
    public void update(String title, String description, Integer id) throws DaoException {
        String sql = "update groups set title = ?, description = ? where id = ?";
        log.debug("Method update send sql request - ID = " + id + ", TITLE = " + title + ", DESCRIPTION = " + description);
        try {
            jdbcExecutor.execUpdate(sql, title, description, id);
        } catch (Exception e) {
            log.error("Exception in update method", e.getCause());
        }
    }

    @Override
    public void deleteAll() throws DaoException {
        String sql = "delete from groups";
        log.debug("Method deleteAll send sql request");
        try {
            jdbcExecutor.execUpdate(sql);
        } catch (Exception e) {
            log.error("Exception in deleteAll method", e.getCause());
        }
    }
}