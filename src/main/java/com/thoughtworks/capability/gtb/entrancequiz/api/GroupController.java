package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GroupController {

    private static final List<Group> groupList = initGroupList();

    static void resetGroupList() {
        groupList.forEach(group -> group.setTraineeList(new ArrayList<>()));
    }

    private static List<Group> initGroupList() {
        return Stream.of(1, 2, 3, 4, 5, 6)
                .map(id -> new Group(id, "Term "+id, new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @GetMapping(path="/groups")
    public List<Group> getGroupList(){
        return groupList;
    }

    @GetMapping(path = "/groups/grouping")
    public void grouping() {
        resetGroupList();

        Random random = new Random();
        List<Trainee> traineeList = new ArrayList<>(TraineeController.traineeList);
        int i = 0;
        while (traineeList.size() > 0) {
            int index = random.nextInt(traineeList.size());
            Trainee trainee = traineeList.get(index);
            groupList.get(i++ % groupList.size()).getTraineeList().add(trainee);
            traineeList.remove(index);
        }
    }

}
