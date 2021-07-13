package com.doan.tma_spring_boot_test.service;

/*
 An endpoint that lists all players on a team
 An endpoint to add a player to a team
 An endpoint to update a player on a team
 An endpoint to delete a player on a team
*/

import com.doan.tma_spring_boot_test.entity.Player;

import java.util.List;

public interface PlayerService {

    public List<Player> getAllPlayers(Integer teamId);

    public Player addPlayer(Player newPlayer, Integer teamId);

    public Player updatePlayer(Player newPlayer, Integer playerId, Integer teamId);

    public List<Player> deletePlayer(Integer playerId, Integer teamId);

}
