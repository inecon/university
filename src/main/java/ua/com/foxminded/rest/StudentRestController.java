package ua.com.foxminded.rest;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student) {
        //change before release!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        HttpHeaders headers = new HttpHeaders();
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.studentDao.create(student);
        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.studentDao.update(student);
        return new ResponseEntity<>(student, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        Student student = this.studentDao.getById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.studentDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentDao.getAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
