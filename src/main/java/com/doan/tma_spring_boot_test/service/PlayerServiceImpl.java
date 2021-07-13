package com.doan.tma_spring_boot_test.service;

import com.doan.tma_spring_boot_test.api.PlayerNotFoundException;
import com.doan.tma_spring_boot_test.api.TeamNotFoundException;
import com.doan.tma_spring_boot_test.entity.Player;
import com.doan.tma_spring_boot_test.repository.PlayerRepository;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    private TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository  teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> getAllPlayers(Integer teamId) {
        List<Player> playerList = playerRepository.findByTeamId(teamId);
        if(playerList.size() == 0) {
            throw new TeamNotFoundException(teamId);
        } else {
            return playerList;
        }
    }

    @Override
    public Player addPlayer(Player newPlayer, Integer teamId) {
        return teamRepository.findById(teamId).map(team -> {
            newPlayer.setTeam(team);
            return playerRepository.save(newPlayer);
        }).orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    @Override
    public Player updatePlayer(Player newPlayer, Integer playerId, Integer teamId) {
        if(!teamRepository.existsById(teamId)) {
            throw new TeamNotFoundException(teamId);
        }

        return playerRepository.findById(playerId)
                .map(player -> {
                    player.setTeam(teamRepository.findById(teamId).get());
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

    @Override
    public List<Player> deletePlayer(Integer playerId, Integer teamId) {
        if(!teamRepository.existsById(teamId)) {
            throw new TeamNotFoundException(teamId);
        }
        if(playerRepository.existsById(playerId)) {
            List<Player> retPlayer = playerRepository.findByTeamId(teamId);
            playerRepository.deleteById(playerId);
            return retPlayer;
        }
        else {
            throw new PlayerNotFoundException(playerId);
        }
    }
}
