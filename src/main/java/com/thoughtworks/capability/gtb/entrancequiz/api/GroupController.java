package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.exception.DuplicateGroupNameException;
import com.thoughtworks.capability.gtb.entrancequiz.exception.NotFoundGroupException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin("*")
public class GroupController {

    @GetMapping(path = "/groups")
    public List<Group> getGroupList() {
        return GroupRepository.getGroupList();
    }

    @GetMapping(path = "/groups/grouping")
    public void grouping() {
        if(GroupRepository.getGroupList() == null){
            GroupRepository.init();
        }

        List<Group> groupList = GroupRepository.getGroupList();
        groupList.forEach(group -> group.setTraineeList(new ArrayList<>()));

        Random random = new Random();
        List<Trainee> traineeList = new ArrayList<>(TraineeRepository.getTraineeList());
        int i = 0;
        while (traineeList.size() > 0) {
            int index = random.nextInt(traineeList.size());
            Trainee trainee = traineeList.get(index);
            groupList.get(i++ % groupList.size()).getTraineeList().add(trainee);
            traineeList.remove(index);
        }
    }

    @PatchMapping(path = "/groups/{id}")
    public void rename(@PathVariable int id, @RequestBody Group group) {
        if (GroupRepository.findOneByName(group.getName()).isPresent()) {
            throw new DuplicateGroupNameException();
        }

        GroupRepository.findOneById(id)
                .orElseThrow(NotFoundGroupException::new)
                .setName(group.getName());
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
