package com.thoughtworks.capability.gtb.entrancequiz.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@CrossOrigin("*")
class TraineeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        TraineeController.resetTraineeList();
    }

    @Test
    void should_get_trainee_list() throws Exception {
        mockMvc.perform(get("/trainees"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("沈乐棋"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("徐慧慧"))
                .andExpect(jsonPath("$.length()").value(35))
                .andExpect(status().isOk());
    }

    @Test
    void should_add_one_trainee() throws Exception {
        mockMvc.perform(post("/trainees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"new trainee\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/trainees"))
                .andExpect(jsonPath("$[35].id").value(36))
                .andExpect(jsonPath("$[35].name").value("new trainee"))
                .andExpect(jsonPath("$.length()").value(36))
                .andExpect(status().isOk());
    }

}