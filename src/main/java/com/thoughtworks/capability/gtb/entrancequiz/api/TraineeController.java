package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Group;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import com.thoughtworks.capability.gtb.entrancequiz.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TraineeController {

    private TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping(path = "/trainees")
    public List<Trainee> getTraineeList() {
        return traineeService.getTraineeList();
    }

    @PostMapping(path="/trainees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrainee(@RequestBody Trainee trainee){
        traineeService.addTrainee(trainee);
    }

}
