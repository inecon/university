package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Lecture;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LectureDaoImpl implements LectureDao {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    private JdbcExecutor<Lecture> jdbcExecutor;
    private static final Logger log = Logger.getLogger(LectureDaoImpl.class);
    GroupDao groupDao = new GroupDaoImpl(connectionFactory);
    TeacherDao teacherDao = new TeacherDaoImpl(connectionFactory);

    public LectureDaoImpl(ConnectionFactory connectionFactory) {
        this.jdbcExecutor = new JdbcExecutor<Lecture>(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Lecture> getAll() throws DaoException {
        Comparator<Lecture> byId = Comparator.comparing(Lecture::getId);
        final String sql = "select * from lectures";
        log.info("Method getAll send sql request");
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                List<Lecture> allLectures = new ArrayList<>();
                while (result.next()) {
                    allLectures.add(new Lecture(result.getInt("id"),
                            (LocalDateTime.parse(result.getString("date"))),
                            result.getString("subject"),
                            teacherDao.getById(result.getInt("teacher_id")),
                            groupDao.getById(result.getInt("group_id")),
                            result.getInt("classroom")));
                }
                allLectures.sort(byId);
                return allLectures;
            });
        } catch (DaoException | SQLException e) {
            log.error("Exception in getAll method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Lecture lecture) throws DaoException {
        final String sql = "insert into lectures (id, date, subject, teacher_id, group_id, classroom) values (?,?,?,?,?,?)";
        log.info("Method create send sql request with ID = " + lecture.getId() + ", DATE = " + lecture.getDate().toString() + ", SUBJECT = " + lecture.getSubject() + ", TEACHER_ID= " +
                lecture.getTeacher().getId() + ", GROUP_ID = " + lecture.getGroup().getId() + ", CLASSROOM = " + lecture.getClassroom());
        try {
            jdbcExecutor.execUpdate(sql, lecture.getId(), lecture.getDate().toString(), lecture.getSubject(), lecture.getTeacher().getId(),
                    lecture.getGroup().getId(), lecture.getClassroom());
        } catch (DaoException | SQLException e) {
            log.error("Exception in create method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Lecture lecture) throws DaoException {
        final String sql = "update lectures set  date = ?, subject = ?, teacher_id = ?, group_id = ?, classroom = ? where id = ?";
        log.info("Method update send sql request with  with ID = " + lecture.getId() + ", DATE DATE = " + lecture.getDate().toString() + ", SUBJECT = " + lecture.getSubject() + ", TEACHER_ID= " +
                lecture.getTeacher().getId() + ", GROUP_ID = " + lecture.getGroup().getId() + ", CLASSROOM = " + lecture.getClassroom());
        try {
            jdbcExecutor.execUpdate(sql, lecture.getDate().toString(), lecture.getSubject(), lecture.getTeacher().getId(),
                    lecture.getGroup().getId(), lecture.getClassroom(), lecture.getId());
        } catch (DaoException | SQLException e) {
            log.error("Exception in update method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteAll() throws DaoException {
        final String sql = "delete from lectures";
        log.info("Method deleteAll send sql request");
        try {
            jdbcExecutor.execUpdate(sql);
        } catch (DaoException | SQLException e) {
            log.error("Exception in deleteAll method", e.getCause());
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(Integer id) throws DaoException {
        final String sql = "delete from lectures where id = ?";
        log.debug("Method deleteById send sql request with id = " + id);
        try {
            jdbcExecutor.execUpdate(sql, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in deleteById method", e.getCause());
            throw new DaoException(e);
        }
    }

    public Lecture getById(Integer id) {
        final String sql = "select * from lectures where id = ?";
        log.debug("Method getById send sql request with ID = " + id);
        try {
            return jdbcExecutor.execQuery(sql, result -> {
                result.next();
                return new Lecture(result.getInt("id"),
                        (LocalDateTime.parse(result.getString("date"))),
                        result.getString("subject"),
                        teacherDao.getById(result.getInt("teacher_id")),
                        groupDao.getById(result.getInt("group_id")),
                        result.getInt("classroom"));
            }, id);
        } catch (DaoException | SQLException e) {
            log.error("Exception in getById method", e.getCause());
            throw new DaoException(e);
        }
    }
}
