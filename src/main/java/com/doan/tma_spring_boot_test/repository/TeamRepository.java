package com.doan.tma_spring_boot_test.repository;

import com.doan.tma_spring_boot_test.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {

}
