package com.doan.tma_spring_boot_test.api;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Integer id) {
        super("Could not find player " + id);
    }
}
