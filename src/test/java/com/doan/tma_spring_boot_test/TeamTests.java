package com.doan.tma_spring_boot_test;

import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeamTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamRepository teamRepository;

    @Test
    public void findAllTeams() throws Exception{
        Team t = new Team("teamName", "cityName", "mascotName");
        List tList = new ArrayList<>();
        tList.add(t);
        when(teamRepository.findAll()).thenReturn(tList);

        this.mockMvc.perform(get("/v1/teams")
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    @Test
    public void findSingleTeam() throws Exception {
        this.mockMvc.perform(get("/v1/teams/1")
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void createTeam() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/v1/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Team("team", "city", "mascot")))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("team"));
    }

    @Test
    public void updateTeam() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(put("/v1/teams/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Team("newTeam", "city", "mascot")))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("newTeam"));
    }

    @Test
    public void deleteTeam() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(delete("/v1/teams/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }
}
