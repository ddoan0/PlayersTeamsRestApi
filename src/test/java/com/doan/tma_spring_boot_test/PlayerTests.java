package com.doan.tma_spring_boot_test;

import com.doan.tma_spring_boot_test.entity.Player;
import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    public void testFindAllPlayers() throws Exception {
        when(playerRepository.findByTeamId(1)).thenReturn(
                List.of(new Player(new Team("team", "city", "mascot"),"name", "position", 21, 80, 140, "college", 20)));

        this.mockMvc.perform(get("/v1/teams/1/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is("name")))
                .andExpect(jsonPath("$[0].position", Matchers.is("position")))
                .andExpect(jsonPath("$[0].age", Matchers.is(21)))
                .andExpect(jsonPath("$[0].height", Matchers.is(80)))
                .andExpect(jsonPath("$[0].weight", Matchers.is(140)))
                .andExpect(jsonPath("$[0].college", Matchers.is("college")))
                .andExpect(jsonPath("$[0].salary", Matchers.is(20)));
    }

    @Test
    public void testSavePlayer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/v1/teams/1/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Player(new Team("team", "city", "mascot"),"name", "position", 21, 80, 140, "college", 20)))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(put("/v1/teams/1/players/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Player(new Team("team", "city", "mascot"),"newName", "position", 21, 80, 140, "college", 20)))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("newName"));
    }

    @Test
    public void testDeletePlayer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(delete("/v1/teams/1/players/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").doesNotExist());
    }
}
