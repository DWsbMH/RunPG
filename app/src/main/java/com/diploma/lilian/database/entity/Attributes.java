package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "attributes")
public class Attributes {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

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
    private int luck;

    @DatabaseField
    private int endurance;

    public Attributes() {
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

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
}
