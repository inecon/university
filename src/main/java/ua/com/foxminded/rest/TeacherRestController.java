package ua.com.foxminded.rest;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.dao.TeacherDao;
import ua.com.foxminded.domain.Teacher;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teachers/")
@Data
public class TeacherRestController {
    @Inject
    private TeacherDao teacherDao;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Teacher> getTeacher(@PathVariable("id") Integer teacherId) {
        if (teacherId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Teacher teacher = this.teacherDao.getById(teacherId);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Teacher> saveTeacher(@RequestBody @Valid Teacher teacher) {
        //modify before release!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        HttpHeaders headers = new HttpHeaders();
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.teacherDao.create(teacher);
        return new ResponseEntity<>(teacher, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Teacher> updateTeacher(@RequestBody @Valid Teacher teacher) {
        HttpHeaders headers = new HttpHeaders();
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.teacherDao.update(teacher);
        return new ResponseEntity<>(teacher, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Integer id) {
        Teacher teacher = this.teacherDao.getById(id);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.teacherDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = this.teacherDao.getAll();
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
