package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("*")
public class TraineeController {

    private static List<Trainee> traineeList;

    static void resetTraineeList(){
        List<String> nameList = Arrays.asList("沈乐棋", "徐慧慧", "陈思聪", "王江林", "王登宇", "杨思雨",
                "江雨舟", "廖燊", "胡晓", "但杰", "盖迈达", "肖美琦", "黄云洁", "齐瑾浩", "刘亮亮", "肖逸凡",
                "王作文", "郭瑞凌", "李明豪", "党泽", "肖伊佐", "贠晨曦", "李康宁", "马庆", "商婕", "余榕", "谌哲", "董翔锐",
                "陈泰宇", "赵允齐", "张柯", "廖文强", "刘轲", "廖浚斌", "凌凤仪");
        traineeList = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            String name = nameList.get(i);
            traineeList.add(new Trainee(i + 1, name));
        }
    }

    static {
        resetTraineeList();
    }

    @GetMapping(path = "/trainees")
    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    @PostMapping(path="/trainees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrainee(@RequestBody Trainee trainee){
        trainee.setId(traineeList.size()+1);
        traineeList.add(trainee);
    }
}
