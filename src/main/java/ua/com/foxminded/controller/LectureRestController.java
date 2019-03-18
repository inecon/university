package ua.com.foxminded.controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.repository.LectureDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/lectures/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
@Slf4j
public class LectureRestController {
    @Inject
    private LectureDao lectureDao;

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional> getLecture(@PathVariable("id") Integer lectureId) {
        Optional<Lecture> lecture = this.lectureDao.findById(lectureId);
        if (lecture.equals(Optional.empty())) {
            log.debug("There no Lecture with ID = " + lectureId + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lecture, HttpStatus.OK);
    }

    @PostMapping ()
    public ResponseEntity<Lecture> saveLecture(@RequestBody @Valid Lecture lecture) {
        HttpHeaders headers = new HttpHeaders();
        this.lectureDao.save(lecture);
        headers.setLocation(URI.create("/api/lectures/" + lecture.getId()));
        return new ResponseEntity<>(lecture, headers, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Lecture> updateLecture(@RequestBody @Valid Lecture lecture) {
        HttpHeaders headers = new HttpHeaders();
        this.lectureDao.save(lecture);
        return new ResponseEntity<>(lecture, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Lecture> deleteLecture(@PathVariable("id") Integer id) {
        Optional<Lecture> lecture = this.lectureDao.findById(id);
        if (lecture == null) {
            log.debug("There no Lecture with ID = " + id + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.lectureDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> lectures = (List<Lecture>) this.lectureDao.findAll(sortByIdAsc());
        if (lectures.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
