package ua.com.foxminded.controller;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Schedule;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.domain.Teacher;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/schedule/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
public class SchedulerRestController {
    @Inject
    Schedule schedule;

    @PostMapping(value = "/students")
    public ResponseEntity<List<Lecture>> getStudentScheduler(@RequestBody @Valid Student student) {
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Lecture> lectures = this.schedule.getStudentScheduledLecturesMonth(student);
        if (lectures == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    @PostMapping(value = "/teachers")
    public ResponseEntity<List<Lecture>> getTeacherScheduler(@RequestBody @Valid Teacher teacher) {
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Lecture> lectures = this.schedule.getTeacherScheduledLecturesMonth(teacher);
        if (lectures == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }
}
