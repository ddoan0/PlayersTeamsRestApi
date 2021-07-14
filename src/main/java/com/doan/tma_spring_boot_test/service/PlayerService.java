package com.doan.tma_spring_boot_test.service;

import com.doan.tma_spring_boot_test.entity.Player;

import java.util.List;

public interface PlayerService {

    public List<Player> getAllPlayers(Integer teamId);

    public Player addPlayer(Player newPlayer, Integer teamId);

    public Player updatePlayer(Player newPlayer, Integer playerId, Integer teamId);

    public List<Player> deletePlayer(Integer playerId, Integer teamId);

}
