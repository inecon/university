package ua.com.foxminded.dao;

import ua.com.foxminded.domain.Lecture;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LectureDaoImpl implements LectureDao {
    ConnectionFactory connectionFactory;
    private Executor<Lecture> executor;

    public LectureDaoImpl(ConnectionFactory connectionFactory) {
        this.executor = new Executor<Lecture>(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<Lecture> getAll() throws SQLException {
        String sql = "select * from lectures";
        GroupDao groupDao = new GroupDaoImpl(connectionFactory);
        TeacherDao teacherDao = new TeacherDaoImpl(connectionFactory);
        return executor.execQuery(sql, result -> {
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
    public void create(Lecture lecture) throws SQLException {
        String sql = "insert into lectures (date, subject, teacher_id, group_id, classroom) values (?,?,?,?,?)";
        executor.execUpdate(sql, lecture.getDate().toString(), lecture.getSubject(), lecture.getTeacher().getId(),
                lecture.getGroup().getId(), lecture.getClassroom());
    }

    @Override
    public void update(Lecture lecture) throws SQLException {
        String sql = "update lectures set  date = ?, subject = ?, teacher_id = ?, group_id = ?, classroom = ? where date = ?";
        executor.execUpdate(sql, lecture.getDate().toString(), lecture.getSubject(), lecture.getTeacher().getId(),
                lecture.getGroup().getId(), lecture.getClassroom());
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "delete from lectures";
        executor.execUpdate(sql);
    }
}
