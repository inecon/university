package ua.com.foxminded.dao;

import org.apache.log4j.Logger;
import ua.com.foxminded.domain.Lecture;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LectureDaoImpl implements LectureDao {
    ConnectionFactory connectionFactory;
    private JdbcExecutor<Lecture> jdbcExecutor;
    private static final Logger log = Logger.getLogger(LectureDaoImpl.class);

    public LectureDaoImpl(ConnectionFactory connectionFactory) {
        this.jdbcExecutor = new JdbcExecutor<Lecture>(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Lecture> getAll() {
        String sql = "select * from lectures";
        log.info("Method getAll send sql");
        GroupDao groupDao = new GroupDaoImpl(connectionFactory);
        TeacherDao teacherDao = new TeacherDaoImpl(connectionFactory);
        return jdbcExecutor.execQuery(sql, result -> {
            List<Lecture> allLectures = new ArrayList<>();
            while (result.next()) {
                allLectures.add(new Lecture((LocalDateTime.parse(result.getString("date"))),
                        result.getString("subject"),
                        teacherDao.getById(result.getInt("teacher_id")),
                        groupDao.getById(result.getInt("group_id")),
                        result.getInt("classroom")));
            }
            return allLectures;
        });
    }

    @Override
    public void create(Lecture lecture) {
        String sql = "insert into lectures (date, subject, teacher_id, group_id, classroom) values (?,?,?,?,?)";
        log.info("Method create - DATE = " + lecture.getDate().toString() + ", SUBJECT = " + lecture.getSubject() + ", TEACHER_ID= " +
                lecture.getTeacher().getId() + ", GROUP_ID = " + lecture.getGroup().getId() + ", CLASSROOM = " + lecture.getClassroom());
        jdbcExecutor.execUpdate(sql, lecture.getDate().toString(), lecture.getSubject(), lecture.getTeacher().getId(),
                lecture.getGroup().getId(), lecture.getClassroom());
    }

    //to do check this
    @Override
    public void update(Lecture lecture) {
        String sql = "update lectures set  date = ?, subject = ?, teacher_id = ?, group_id = ?, classroom = ? where date = ?";
        log.info("Method update - DATE = " + lecture.getDate().toString() + ", SUBJECT = " + lecture.getSubject() + ", TEACHER_ID= " +
                lecture.getTeacher().getId() + ", GROUP_ID = " + lecture.getGroup().getId() + ", CLASSROOM = " + lecture.getClassroom());
        jdbcExecutor.execUpdate(sql, lecture.getDate().toString(), lecture.getSubject(), lecture.getTeacher().getId(),
                lecture.getGroup().getId(), lecture.getClassroom());
    }

    @Override
    public void deleteAll() {
        String sql = "delete from lectures";
        log.info("Метод delete отправил запрос в БД");
        jdbcExecutor.execUpdate(sql);
    }
}
