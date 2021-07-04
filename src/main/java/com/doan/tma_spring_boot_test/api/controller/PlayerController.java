package com.doan.tma_spring_boot_test.api.controller;

import com.doan.tma_spring_boot_test.api.TeamNotFoundException;
import com.doan.tma_spring_boot_test.entity.Player;
import com.doan.tma_spring_boot_test.repository.PlayerRepository;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/v1/teams/{teamId}/players")
    public Page<Player> getAllPlayersByTeamId(@PathVariable (value = "teamId") Integer teamId, Pageable pageable) {
        return playerRepository.findByTeamId(teamId, pageable);
    }

    @PostMapping("/v1/teams/{teamId}/players")
    public Player createPlayer(@PathVariable Integer teamId, @RequestBody Player player) {
        return teamRepository.findById(teamId).map(team -> {
            player.setTeam(team);
            return playerRepository.save(player);
        }).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    @PutMapping("/v1/teams/{teamId}/players/{playerId}")
    public Player updatePlayer(@PathVariable Integer teamId,
                               @PathVariable Integer playerId,
                               @RequestBody Player newPlayer) {
        if(!teamRepository.existsById(teamId)) {
            throw new TeamNotFoundException(teamId);
        }

        return playerRepository.findById(playerId)
                .map(player -> {
                    player.setTeam(newPlayer.getTeam());
                    player.setAge(newPlayer.getAge());
                    player.setCollege(newPlayer.getCollege());
                    player.setName(newPlayer.getName());
                    player.setHeight(newPlayer.getHeight());
                    player.setPosition(newPlayer.getPosition());
                    player.setSalary(newPlayer.getSalary());
                    player.setWeight(newPlayer.getWeight());
                    return playerRepository.save(player);
                })
                .orElseGet(() -> {
                    newPlayer.setId(playerId);
                    return playerRepository.save(newPlayer);
                });
    }

    @DeleteMapping("/v1/teams/{teamId}/players/{playerId}")
    public void deletePlayer(@PathVariable Integer teamId,
                             @PathVariable Integer playerId) {
//        return playerRepository.findByIdAndTeamId(teamId, playerId).map(player -> {
//            playerRepository.delete(player);
//            return null;
//        }).orElseThrow(() -> new TeamNotFoundException(playerId));
        playerRepository.deleteById(playerId);
    }
}
