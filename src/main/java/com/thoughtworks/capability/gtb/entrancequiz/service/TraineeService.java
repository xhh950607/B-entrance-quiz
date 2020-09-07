package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.repository.TraineeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TraineeService {

    public List<Trainee> getTraineeList(){
        return TraineeRepository.getTraineeList();
    }

    public void addTrainee(Trainee trainee){
        trainee.setId(TraineeRepository.getTraineeList().size()+1);
        TraineeRepository.add(trainee);
    }
}
