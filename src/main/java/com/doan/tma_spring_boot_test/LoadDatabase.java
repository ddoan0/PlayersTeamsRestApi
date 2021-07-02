package com.doan.tma_spring_boot_test;

import com.doan.tma_spring_boot_test.entity.Team;
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
    CommandLineRunner initDatabase(TeamRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Team("Hawks", "Atlanta", "Harry")));
            log.info("Preloading " + repository.save(new Team("Hornets", "Charlotte", "Hugo")));
        };
    }
}
