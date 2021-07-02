package com.doan.tma_spring_boot_test.api;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(Integer id) {
        super("Could not find team " + id);
    }
}
