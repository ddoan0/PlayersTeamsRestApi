package com.doan.tma_spring_boot_test.service;

import com.doan.tma_spring_boot_test.entity.Player;
import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.PlayerRepository;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PlayerServiceTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    private PlayerService playerService;
    private TeamService teamService;

    private Player p1;
    private Team t1;

    @BeforeEach
    public void before() {
        playerService = new PlayerServiceImpl(playerRepository, teamRepository);
        teamService = new TeamServiceImpl(teamRepository);
        t1 = new Team("TestTeamName", "TestTeamCity", "TestTeamMascot");
        p1 = new Player(t1,"Trae Young", "Point Guard", 22, 85, 180, "Oklahoma", 100000);
        playerRepository.save(p1);
        teamRepository.save(t1);
    }

    @Test
    public void testGetAllPlayers() {
        List<Player> expectedPlayerList = new ArrayList<>();
        expectedPlayerList.add(p1);

        assertThat(playerService.getAllPlayers(t1.getId()))
                .isEqualTo(expectedPlayerList);
    }

}
