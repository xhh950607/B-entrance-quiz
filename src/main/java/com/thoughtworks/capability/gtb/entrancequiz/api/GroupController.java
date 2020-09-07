package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.exception.DuplicateGroupNameException;
import com.thoughtworks.capability.gtb.entrancequiz.exception.NotFoundGroupException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/groups")
    public List<Group> getGroupList() {
        return groupService.getGroupList();
    }

    @GetMapping(path = "/groups/grouping")
    public void grouping() {
        groupService.grouping();
    }

    @PatchMapping(path = "/groups/{id}")
    public void rename(@PathVariable int id, @RequestBody Group group) {
        groupService.rename(id, group.getName());
    }

    @ExceptionHandler(NotFoundGroupException.class)
    public ResponseEntity handleNotFoundGroupException(NotFoundGroupException ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(DuplicateGroupNameException.class)
    public ResponseEntity handleDuplicateGroupNameException(DuplicateGroupNameException ex) {
        return ResponseEntity.status(409).build();
    }

}
