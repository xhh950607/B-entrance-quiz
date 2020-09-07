package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TraineeController {

    @GetMapping(path = "/trainees")
    public List<Trainee> getTraineeList() {
        return TraineeRepository.getTraineeList();
    }

    @PostMapping(path="/trainees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrainee(@RequestBody Trainee trainee){
        trainee.setId(TraineeRepository.getTraineeList().size()+1);
        TraineeRepository.add(trainee);
    }

}
