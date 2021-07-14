package com.doan.tma_spring_boot_test.api.controller;

import com.doan.tma_spring_boot_test.api.controller.PlayerController;
import com.doan.tma_spring_boot_test.entity.Player;
import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    private static final Integer teamId = 1;
    private static final String url = "/v1/teams/" + teamId + "/players";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;
    private Team t1;
    private Player p1;


    @BeforeEach
    public void before() {
         t1 = new Team("TestTeamName", "TestTeamCity", "TestTeamMascot");
         p1 = new Player(t1,"Trae Young", "Point Guard", 22, 85, 180, "Oklahoma", 100000);
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        List<Player> expectedPlayerList = new ArrayList<>();
        expectedPlayerList.add(p1);

        given(playerService.getAllPlayers(teamId))
                .willReturn(expectedPlayerList);

        mockMvc.perform(
                get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedPlayerList)));

        verify(playerService, Mockito.times(1)).getAllPlayers(teamId);

    }

    @Test
    public void testAddPlayer() throws Exception {
        given(playerService.addPlayer(p1, teamId)).willReturn(p1);

        MockHttpServletRequestBuilder request = post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(p1));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));

        verify(playerService, Mockito.times(1)).addPlayer(p1, teamId);
    }



}
