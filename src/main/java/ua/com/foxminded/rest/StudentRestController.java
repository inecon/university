package ua.com.foxminded.rest;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.dao.StudentDao;
import ua.com.foxminded.domain.Student;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students/")
@Data
public class StudentRestController {
    @Inject
    private StudentDao studentDao;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") Integer studentId) {
        if (studentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = this.studentDao.getById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student) {
        //change before release!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        HttpHeaders headers = new HttpHeaders();
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.studentDao.create(student);
        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student) {
        //for a future improvements
        HttpHeaders headers = new HttpHeaders();
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.studentDao.update(student);
        return new ResponseEntity<>(student, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        Student student = this.studentDao.getById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.studentDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentDao.getAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
