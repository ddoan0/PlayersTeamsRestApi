package com.doan.tma_spring_boot_test;

import com.doan.tma_spring_boot_test.entity.Player;
import com.doan.tma_spring_boot_test.entity.Team;
import com.doan.tma_spring_boot_test.repository.PlayerRepository;
import com.doan.tma_spring_boot_test.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepository, PlayerRepository playerRepository) {
        Team hawks = new Team("Hawks", "Atlanta", "Harry");
        Team hornets = new Team("Hornets", "Charlotte", "Hugo");

        Player p1 = new Player(hawks,"Trae Young", "Point Guard", 22, 85, 180, "Oklahoma", 100000);
        Player p2 = new Player(hornets, "Gordon Hayward", "Shooting Guard", 31, 91, 225, "Butler", 100000);

        return args -> {
            log.info("Preloading team " + teamRepository.save(hawks));
            log.info("Preloading team " + teamRepository.save(hornets));
            log.info("Preloading player " + playerRepository.save(p1));
            log.info("Preloading player " + playerRepository.save(p2));
        };
    }
}
