package com.doan.tma_spring_boot_test.api.controller;

import com.doan.tma_spring_boot_test.api.TeamNotFoundException;
import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private final TeamRepository repository;

    TeamController(TeamRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/v1/teams")
    List<Team> all() {
        return repository.findAll();
    }

    @GetMapping("/v1/teams/search")
    List<Team> findByQuery(@RequestParam(required = false) String name,
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String mascot) {
        List<Team> nameFind = repository.findByNameIs(name);
        List<Team> cityFind = repository.findByCityIs(city);
        List<Team> mascotFind = repository.findByMascotIs(mascot);
        if(name != null && nameFind.size() > 0) {
            return nameFind;
        } else if (city != null && cityFind.size() > 0) {
            return cityFind;
        } else if (mascot != null && mascotFind.size() > 0) {
            return mascotFind;
        } else {
            throw new TeamNotFoundException();
        }
    }

    @PostMapping("/v1/teams")
    Team newTeam(@RequestBody Team newTeam) {
        return repository.save(newTeam);
    }

    @GetMapping("/v1/teams/{id}")
    Team one(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));
    }

    @PutMapping("/v1/teams/{id}")
    Team replaceTeam(@RequestBody Team newTeam, @PathVariable Integer id) {
        return repository.findById(id)
                .map(team -> {
                    team.setName(newTeam.getName());
                    team.setCity(newTeam.getCity());
                    team.setMascot(newTeam.getMascot());
                    return repository.save(team);
                })
                .orElseGet(() -> {
                    newTeam.setId(id);
                    return repository.save(newTeam);
                });
    }

    @DeleteMapping("/v1/teams/{id}")
    void deleteTeam(@PathVariable Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new TeamNotFoundException(id);
        }
    }

}
