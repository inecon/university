package ua.com.foxminded.controller;


import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.repository.StudentDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RepositoryRestController
@RequestMapping(value = "/api/students/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
@Log4j

public class StudentRestController {
    @Inject
    private StudentDao studentDao;

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional> getStudent(@PathVariable("id") @Valid Integer studentId) {
        Optional<Student> student = this.studentDao.findById(studentId);
        if (student.equals(Optional.empty())) {
            log.debug("There no Student with ID = " + studentId + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student) {
        this.studentDao.save(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/students/" + student.getId()));

        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student) {
        //for a future improvements
        HttpHeaders headers = new HttpHeaders();
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.studentDao.save(student);
        return new ResponseEntity<>(student, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        Optional<Student> student = this.studentDao.findById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.studentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = (List<Student>) this.studentDao.findAll(sortByIdAsc());
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
