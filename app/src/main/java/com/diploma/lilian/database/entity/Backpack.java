package com.diploma.lilian.database.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "backpack")
public class Backpack {

    private static final int MAX_WEAPON_CAPACITY = 8;
    private static final int MAX_POTION_CAPACITY = 8;

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Weapon> weapons;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Potion> potions;

    public Backpack() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ForeignCollection<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ForeignCollection<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ForeignCollection<Potion> getPotions() {
        return potions;
    }

    public void setPotions(ForeignCollection<Potion> potions) {
        this.potions = potions;
    }

    public static int getMaxWeaponCapacity() {
        return MAX_WEAPON_CAPACITY;
    }

    public static int getMaxPotionCapacity() {
        return MAX_POTION_CAPACITY;
    }
}
