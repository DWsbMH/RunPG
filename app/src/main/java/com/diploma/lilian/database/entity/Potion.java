package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "potion")
public class Potion extends Item {

    @DatabaseField
    private int durability;

    @DatabaseField
    private PotionType potionType;

    public Potion() {
    }

    public Potion(Backpack backpack, String name, String imageName, int durability, PotionType potionType) {
        super(backpack, name, imageName);
        this.durability = durability;
        this.potionType = potionType;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public PotionType getPotionType() {
        return potionType;
    }

    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
    }
}
