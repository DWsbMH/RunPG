package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Date;

@DatabaseTable(tableName = "player")
public class Player {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "last_played", dataType = DataType.DATE)
    private Date lastPlayed;

    @DatabaseField
    private int maxHealthPoint;

    @DatabaseField
    private int actualHealthPoint;

    @DatabaseField(defaultValue = "1")
    private int level;

    @DatabaseField
    private int experienceGained;

    @DatabaseField
    private int experienceNeeded;

    @DatabaseField
    private int maxStamina;

    @DatabaseField
    private int actualStamina;

    @DatabaseField
    private int strength;

    @DatabaseField
    private int speed;

    @DatabaseField
    private int endurance;

    @ForeignCollectionField(eager = true)
    private Collection<SportActivity> sportActivities;

    public Player() {
    }

    public Player(int id, String name, Date lastPlayed, int maxHealthPoint, int actualHealthPoint, int level, int experienceGained, int experienceNeeded, int maxStamina, int actualStamina, int strength, int speed, int endurance, Collection<SportActivity> item) {
        this.id = id;
        this.name = name;
        this.lastPlayed = lastPlayed;
        this.maxHealthPoint = maxHealthPoint;
        this.actualHealthPoint = actualHealthPoint;
        this.level = level;
        this.experienceGained = experienceGained;
        this.experienceNeeded = experienceNeeded;
        this.maxStamina = maxStamina;
        this.actualStamina = actualStamina;
        this.strength = strength;
        this.speed = speed;
        this.endurance = endurance;
        this.sportActivities = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getActualHealthPoint() {
        return actualHealthPoint;
    }

    public void setActualHealthPoint(int actualHealthPoint) {
        this.actualHealthPoint = actualHealthPoint;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperienceGained() {
        return experienceGained;
    }

    public void setExperienceGained(int experienceGained) {
        this.experienceGained = experienceGained;
    }

    public int getExperienceNeeded() {
        return experienceNeeded;
    }

    public void setExperienceNeeded(int experienceNeeded) {
        this.experienceNeeded = experienceNeeded;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }

    public int getActualStamina() {
        return actualStamina;
    }

    public void setActualStamina(int actualStamina) {
        this.actualStamina = actualStamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public Collection<SportActivity> getSportActivities() {
        return sportActivities;
    }

    public void setSportActivities(Collection<SportActivity> sportActivities) {
        this.sportActivities = sportActivities;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastPlayed=" + lastPlayed +
                ", maxHealthPoint=" + maxHealthPoint +
                ", actualHealthPoint=" + actualHealthPoint +
                ", level=" + level +
                ", experienceGained=" + experienceGained +
                ", experienceNeeded=" + experienceNeeded +
                ", maxStamina=" + maxStamina +
                ", actualStamina=" + actualStamina +
                ", strength=" + strength +
                ", speed=" + speed +
                ", endurance=" + endurance +
                '}';
    }
}