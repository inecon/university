package ua.com.foxminded.controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.foxminded.domain.Group;
import ua.com.foxminded.repository.GroupDao;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/api/groups/", produces = APPLICATION_JSON_UTF8_VALUE)
@Data
@Slf4j
public class GroupRestController {
    @Inject
    private GroupDao groupDao;
    /**
     * This method is used to get Group by ID.
     * @param groupId This is the Group ID to find lecture in DB
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional> getGroup(@PathVariable("id") Integer groupId) {
        Optional<Group> group = this.groupDao.findById(groupId);
        if (group.equals(Optional.empty())) {
            log.debug("There no Group with ID = " + groupId + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
    /**
     * This method is used to write group to DB.
     * @param group This is the group to add to DB
     */
    @PostMapping
    public ResponseEntity<Group> saveGroup(@RequestBody @Valid Group group) {
        HttpHeaders headers = new HttpHeaders();
        this.groupDao.save(group);
        headers.setLocation(URI.create("/api/groups/" + group.getId()));
        return new ResponseEntity<>(group, headers, HttpStatus.CREATED);
    }
    /**
     * This method is used to UPDATE group in DB.
     * @param group This is the group to UPDATE in DB
     */
    @PutMapping
    public ResponseEntity<Group> updateGroup(@RequestBody @Valid Group group) {
        HttpHeaders headers = new HttpHeaders();
        this.groupDao.save(group);
        return new ResponseEntity<>(group, headers, HttpStatus.OK);
    }
    /**
     * This method is used to DELETE group from DB.
     * @param id This is ID of the group which will be DELETE from DB
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("id") Integer id) {
        Optional<Group> group = this.groupDao.findById(id);
        if (group == null) {
            log.debug("There no Group with ID = " + id + " found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.groupDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /**
     * This method is used to get all groups from DB.
     */
    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = (List<Group>) this.groupDao.findAll(sortByIdAsc());
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
