package ua.com.foxminded.controller;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Teacher;
import ua.com.foxminded.repository.TeacherDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/teachers/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
public class TeacherRestController {
    @Inject
    private TeacherDao teacherDao;

    @GetMapping(value = "{id}")
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

    @PostMapping(value = "")
    public ResponseEntity<Teacher> saveTeacher(@RequestBody @Valid Teacher teacher) {
        //modify before release!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        HttpHeaders headers = new HttpHeaders();
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.teacherDao.create(teacher);
        return new ResponseEntity<>(teacher, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody @Valid Teacher teacher) {
        HttpHeaders headers = new HttpHeaders();
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.teacherDao.update(teacher);
        return new ResponseEntity<>(teacher, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Integer id) {
        Teacher teacher = this.teacherDao.getById(id);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.teacherDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = this.teacherDao.getAll();
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
