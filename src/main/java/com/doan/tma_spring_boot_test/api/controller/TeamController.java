package com.doan.tma_spring_boot_test.api.controller;

import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.service.TeamService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/v1/teams")
    List<Team> all() {
        return teamService.getAllTeams();
    }

    @GetMapping("/v1/teams/search")
    List<Team> findByQuery(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String mascot) {
        return teamService.getTeamByQuery(name, city, mascot);
    }

    @PostMapping("/v1/teams")
    Team newTeam(@RequestBody Team newTeam) {
        return teamService.addTeam(newTeam);
    }

    @GetMapping("/v1/teams/{id}")
    Team one(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @PutMapping("/v1/teams/{id}")
    Team replaceTeam(@RequestBody Team newTeam, @PathVariable Integer id) {
        return teamService.updateTeam(newTeam, id);
    }

    @DeleteMapping("/v1/teams/{id}")
    Optional<Team> deleteTeam(@PathVariable Integer id) {
        return teamService.deleteTeam(id);
    }

}
