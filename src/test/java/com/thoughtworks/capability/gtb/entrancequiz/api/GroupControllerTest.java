package com.thoughtworks.capability.gtb.entrancequiz.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GroupControllerTest {

    @Autowired
    MockMvc mockMvc;

    @AfterEach
    void clearDown(){
        GroupController.reset();
    }

    @Test
    void should_get_groups() throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(jsonPath("$.length()").value(6))
                .andExpect(jsonPath("$[0].name").value("Term 1"))
                .andExpect(status().isOk());
    }

    @Test
    void should_grouping() throws Exception {
        mockMvc.perform(get("/groups/grouping"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/groups"))
                .andExpect(jsonPath("$[0].traineeList.length()").value(6))
                .andExpect(jsonPath("$[1].traineeList.length()").value(6))
                .andExpect(jsonPath("$[2].traineeList.length()").value(6))
                .andExpect(jsonPath("$[3].traineeList.length()").value(6))
                .andExpect(jsonPath("$[4].traineeList.length()").value(6))
                .andExpect(jsonPath("$[5].traineeList.length()").value(5))
                .andExpect(status().isOk());
    }

    @Test
    void should_rename_group() throws Exception {
        mockMvc.perform(patch("/groups/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"T.O.P\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/groups"))
                .andExpect(jsonPath("$[0].name").value("T.O.P"))
                .andExpect(status().isOk());
    }
}