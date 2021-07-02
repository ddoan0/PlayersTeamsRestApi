package com.doan.tma_spring_boot_test.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String city;
    private String mascot;

    Team() {}

    public Team(String name, String city, String mascot) {
        this.name = name;
        this.city = city;
        this.mascot = mascot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id.equals(team.id) && name.equals(team.name) && city.equals(team.city) && mascot.equals(team.mascot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, mascot);
    }
}
