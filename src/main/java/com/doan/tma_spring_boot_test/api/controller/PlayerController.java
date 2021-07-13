package com.doan.tma_spring_boot_test.api.controller;

import com.doan.tma_spring_boot_test.entity.Player;

import com.doan.tma_spring_boot_test.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/v1/teams/{teamId}/players")
    public List<Player> getAllPlayersByTeamId(@PathVariable (value = "teamId") Integer teamId) {
        return playerService.getAllPlayers(teamId);
    }

    @PostMapping("/v1/teams/{teamId}/players")
    public Player createPlayer(@PathVariable Integer teamId, @RequestBody Player player) {
        return playerService.addPlayer(player, teamId);
    }

    @PutMapping("/v1/teams/{teamId}/players/{playerId}")
    public Player updatePlayer(@PathVariable Integer teamId,
                               @PathVariable Integer playerId,
                               @RequestBody Player newPlayer) {
        return playerService.updatePlayer(newPlayer, playerId, teamId);
    }

    @DeleteMapping("/v1/teams/{teamId}/players/{playerId}")
    public List<Player> deletePlayer(@PathVariable Integer teamId,
                                     @PathVariable Integer playerId) {
        return playerService.deletePlayer(playerId, teamId);
    }
}
