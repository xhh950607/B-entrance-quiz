package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.repository.GroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
        GroupRepository.init();
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

    @Test
    void should_bad_request_when_rename_group_given_invalid_id() throws Exception {
        mockMvc.perform(patch("/groups/7")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"T.O.P\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_409_when_rename_group_given_duplicate_name() throws Exception {
        mockMvc.perform(patch("/groups/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Term 2\"}"))
                .andExpect(status().is(409));
    }
}