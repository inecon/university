package ua.com.foxminded.controller;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.repository.LectureDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/lectures/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
public class LectureRestController {
    @Inject
    private LectureDao lectureDao;

    @GetMapping(value = "{id}")
    public ResponseEntity<Lecture> getLecture(@PathVariable("id") Integer lectureId) {
        if (lectureId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Lecture lecture = this.lectureDao.getById(lectureId);
        if (lecture == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lecture, HttpStatus.OK);
    }

    @PostMapping (value = "")
    public ResponseEntity<Lecture> saveLecture(@RequestBody @Valid Lecture lecture) {
        //modify before release!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        HttpHeaders headers = new HttpHeaders();
        if (lecture == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.lectureDao.create(lecture);
        return new ResponseEntity<>(lecture, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<Lecture> updateLecture(@RequestBody @Valid Lecture lecture) {
        HttpHeaders headers = new HttpHeaders();
        if (lecture == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.lectureDao.update(lecture);
        return new ResponseEntity<>(lecture, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Lecture> deleteLecture(@PathVariable("id") Integer id) {
        Lecture lecture = this.lectureDao.getById(id);
        if (lecture == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.lectureDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = this.lectureDao.getAll();
        if (lectures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }
}
