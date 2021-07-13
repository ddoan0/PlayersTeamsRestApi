package com.doan.tma_spring_boot_test.service;

import com.doan.tma_spring_boot_test.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    public List<Team> getAllTeams();

    public Team getTeamById(Integer teamId);

    public Team addTeam(Team newTeam);

    public Team updateTeam(Team newTeam, Integer teamId);

    public Optional<Team> deleteTeam(Integer teamId);

    public List<Team> getTeamByQuery(String name, String city, String mascot);

}
