package com.doan.tma_spring_boot_test.repository;

import com.doan.tma_spring_boot_test.entity.Team;

import java.util.List;

public interface TeamRepositoryCustom {

    List<Team> teamByParams(String name, String city, String mascot);
}
