package com.diploma.lilian.database.entity;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "player_sheet")
public class PlayerSheet {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Weapon weapon;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Potion strength;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Potion endurance;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Potion luck;

    public PlayerSheet() {
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Potion getStrength() {
        return strength;
    }

    private void setStrength(Potion strength) {
        this.strength = strength;
    }

    public Potion getEndurance() {
        return endurance;
    }

    private void setEndurance(Potion endurance) {
        this.endurance = endurance;
    }

    public Potion getLuck() {
        return luck;
    }

    private void setLuck(Potion luck) {
        this.luck = luck;
    }

    public void setPotion(Potion item) {
        switch (item.getPotionType()){
            case LUCK:
                setLuck(item);
                break;
            case STRENGTH:
                setStrength(item);
                break;
            case ENDURANCE:
                setEndurance(item);
                break;
        }
    }

    public void deleteEndurancePotion() {
        endurance = null;
    }

    public void deleteStrengthPotion() {
        strength = null;
    }

    public void deleteLuckPotion() {
        luck = null;
    }
}
