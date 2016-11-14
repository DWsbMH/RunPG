package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "weapon")
public class Weapon extends Item{

    @DatabaseField
    private int minDamage;

    @DatabaseField
    private int maxDamage;

    public Weapon() {
    }

    public Weapon(Backpack backpack, String name, String imageName, int minDamage, int maxDamage) {
        super(backpack, name, imageName);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }
}
