package ua.com.foxminded.controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class StudentRestController {
    @Inject
    private StudentDao studentDao;
    /**
     * This method is used to get student by ID.
     * @param studentId This is the Student ID to find student in DB
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional> getStudent(@PathVariable("id") @Valid Integer studentId) {
        Optional<Student> student = this.studentDao.findById(studentId);
        if (student.equals(Optional.empty())) {
            log.debug("There no Student with ID = " + studentId + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    /**
     * This method is used to write student to DB.
     * @param student This is the Student to add to DB
     */
    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student) {
        this.studentDao.save(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/students/" + student.getId()));
        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);
    }
    /**
     * This method is used to UPDATE student in DB.
     * @param student This is the Student to UPDATE in DB
     */
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student) {
        //for a future improvements
        HttpHeaders headers = new HttpHeaders();
        this.studentDao.save(student);
        return new ResponseEntity<>(student, headers, HttpStatus.OK);
    }
    /**
     * This method is used to DELETE student from DB.
     * @param id This is ID of the Student which will be DELETE from DB
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
        Optional<Student> student = this.studentDao.findById(id);
        if (student == null) {
            log.debug("There no Student with ID = " + id + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.studentDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /**
     * This method is used to get all Students from DB.
     */
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
