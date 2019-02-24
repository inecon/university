package ua.com.foxminded.controller;


import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.repository.StudentDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students/")
@Data
public class StudentRestController {
    @Inject
    private StudentDao studentDao;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Optional> getStudent(@PathVariable("id") @Valid Integer studentId) throws Exception {
        if (studentId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        @Valid
        Optional<Student> student = this.studentDao.findById(studentId);
        if (student.equals(Optional.empty())) {
          throw new Exception("There no Student with ID = " + studentId + " found", new Throwable("NOT FOUND"));
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

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
