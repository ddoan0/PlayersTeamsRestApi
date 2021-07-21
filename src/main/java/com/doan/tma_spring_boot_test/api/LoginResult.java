package com.doan.tma_spring_boot_test.api;


import org.springframework.lang.NonNull;

import java.util.Objects;

public class LoginResult {

    public LoginResult(@NonNull String jwt) {
        this.jwt = jwt;
    }

    @NonNull
    private String jwt;

    @NonNull
    public String getJwt() {
        return jwt;
    }

    public void setJwt(@NonNull String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "jwt='" + jwt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResult that = (LoginResult) o;
        return jwt.equals(that.jwt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwt);
    }
}
