package ua.com.foxminded.controller;


import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Lecture;
import ua.com.foxminded.domain.Schedule;
import ua.com.foxminded.domain.Student;
import ua.com.foxminded.domain.Teacher;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/schedule/")
@Data
public class SchedulerRestController {
    @Inject
    Schedule schedule;

    @PostMapping(value = "/students", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @PostMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
