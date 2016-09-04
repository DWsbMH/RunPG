package com.diploma.lilian.database.entity;

import java.util.Collection;
import java.util.Date;

public class PlayerBuilder {
    private int id;
    private String name;
    private Date lastPlayed;
    private int maxHealthPoint;
    private int actualHealthPoint;
    private int level;
    private int experienceGained;
    private int experienceNeeded;
    private int maxStamina;
    private int actualStamina;
    private int strength;
    private int speed;
    private int endurance;
    private Collection<SportActivity> sportActivities;

    public PlayerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PlayerBuilder setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
        return this;
    }

    public PlayerBuilder setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
        return this;
    }

    public PlayerBuilder setActualHealthPoint(int actualHealthPoint) {
        this.actualHealthPoint = actualHealthPoint;
        return this;
    }

    public PlayerBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public PlayerBuilder setExperienceGained(int experienceGained) {
        this.experienceGained = experienceGained;
        return this;
    }

    public PlayerBuilder setExperienceNeeded(int experienceNeeded) {
        this.experienceNeeded = experienceNeeded;
        return this;
    }

    public PlayerBuilder setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public PlayerBuilder setActualStamina(int actualStamina) {
        this.actualStamina = actualStamina;
        return this;
    }

    public PlayerBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public PlayerBuilder setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public PlayerBuilder setEndurance(int endurance) {
        this.endurance = endurance;
        return this;
    }

    public PlayerBuilder setSportActivities(Collection<SportActivity> sportActivities) {
        this.sportActivities = sportActivities;
        return this;
    }

    public Player createPlayer() {
        return new Player(id, name, lastPlayed, maxHealthPoint, actualHealthPoint, level, experienceGained, experienceNeeded, maxStamina, actualStamina, strength, speed, endurance, sportActivities);
    }
}