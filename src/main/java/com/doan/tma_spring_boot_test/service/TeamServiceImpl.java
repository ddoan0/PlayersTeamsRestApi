package com.doan.tma_spring_boot_test.service;

import com.doan.tma_spring_boot_test.api.TeamNotFoundException;
import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Integer teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    @Override
    public Team addTeam(Team newTeam) {
        return teamRepository.save(newTeam);
    }

    @Override
    public Team updateTeam(Team newTeam, Integer teamId) {
        return teamRepository.findById(teamId)
                .map(team -> {
                    team.setName(newTeam.getName());
                    team.setCity(newTeam.getCity());
                    team.setMascot(newTeam.getMascot());
                    return teamRepository.save(team);
                })
                .orElseGet(() -> {
                    newTeam.setId(teamId);
                    return teamRepository.save(newTeam);
                });
    }

    @Override
    public Optional<Team> deleteTeam(Integer teamId) {
        if(teamRepository.existsById(teamId)) {
            Optional<Team> retTeam = teamRepository.findById(teamId);
            teamRepository.deleteById(teamId);
            return retTeam;
        }
        else {
            throw new TeamNotFoundException(teamId);
        }
    }

    @Override
    public List<Team> getTeamByQuery(String name, String city, String mascot) {
        return teamRepository.teamByParams(name, city, mascot);
    }
}
