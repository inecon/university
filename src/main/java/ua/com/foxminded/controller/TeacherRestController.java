package ua.com.foxminded.controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Teacher;
import ua.com.foxminded.repository.TeacherDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/teachers/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
@Slf4j
public class TeacherRestController {
    @Inject
    private TeacherDao teacherDao;

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional> getTeacher(@PathVariable("id") Integer teacherId) {
        Optional<Teacher> teacher = this.teacherDao.findById(teacherId);
        if (teacher.equals(Optional.empty())) {
            log.debug("There no Teacher with ID = " + teacherId + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Teacher> saveTeacher(@RequestBody @Valid Teacher teacher) {
        HttpHeaders headers = new HttpHeaders();
        this.teacherDao.save(teacher);
        headers.setLocation(URI.create("/api/teachers/" + teacher.getId()));
        return new ResponseEntity<>(teacher, headers, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Teacher> updateTeacher(@RequestBody @Valid Teacher teacher) {
        HttpHeaders headers = new HttpHeaders();
        this.teacherDao.save(teacher);
        return new ResponseEntity<>(teacher, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Integer id) {
        Optional<Teacher> teacher = this.teacherDao.findById(id);
        if (teacher == null) {
            log.debug("There no Teacher with ID = " + id + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.teacherDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = (List<Teacher>) this.teacherDao.findAll(sortByIdAsc());
        if (teachers.isEmpty()) {
            log.debug("There not found Teachers");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
