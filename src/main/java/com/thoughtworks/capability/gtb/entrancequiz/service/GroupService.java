package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.exception.DuplicateGroupNameException;
import com.thoughtworks.capability.gtb.entrancequiz.exception.NotFoundGroupException;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GroupService {

    public List<Group> getGroupList() {
        return GroupRepository.getGroupList();
    }

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

    public void rename(int id, String name) {
        if (GroupRepository.findOneByName(name).isPresent()) {
            throw new DuplicateGroupNameException();
        }

        GroupRepository.findOneById(id)
                .orElseThrow(NotFoundGroupException::new)
                .setName(name);
    }
}
