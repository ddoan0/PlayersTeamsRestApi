package com.doan.tma_spring_boot_test.repository;

import com.doan.tma_spring_boot_test.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer>, TeamRepositoryCustom {
    List<Team> findByNameIs(String name);
    List<Team> findByCityIs(String city);
    List<Team> findByMascotIs(String mascot);
}
