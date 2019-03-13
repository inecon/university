package ua.com.foxminded.controller;


import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.repository.GroupDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/groups/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
public class GroupRestController {
    @Inject
    private GroupDao groupDao;

    @GetMapping(value = "{id}")
    public ResponseEntity<Group> getGroup(@PathVariable("id") Integer groupId) {
        if (groupId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Group group = this.groupDao.getById(groupId);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Group> saveLecture(@RequestBody @Valid Group group) {
        HttpHeaders headers = new HttpHeaders();
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.groupDao.create(group);
        return new ResponseEntity<>(group, headers, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Group> updateLecture(@RequestBody @Valid Group group) {
        HttpHeaders headers = new HttpHeaders();
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.groupDao.update(group);
        return new ResponseEntity<>(group, headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Integer id) {
        Group group = this.groupDao.getById(id);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.groupDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = this.groupDao.getAll();
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
