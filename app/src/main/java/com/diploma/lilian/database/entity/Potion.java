package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "potion")
public class Potion extends Item {

    @DatabaseField
    private PotionType potionType;

    @DatabaseField
    private PotionEffect effect;

    public Potion() {
    }

    public Potion(Backpack backpack, String name, String imageName, PotionType potionType, PotionEffect effect) {
        super(backpack, name, imageName);
        this.potionType = potionType;
        this.effect = effect;
    }

    public PotionType getPotionType() {
        return potionType;
    }

    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
    }

    public PotionEffect getEffect() {
        return effect;
    }

    public void setEffect(PotionEffect effect) {
        this.effect = effect;
    }
}
