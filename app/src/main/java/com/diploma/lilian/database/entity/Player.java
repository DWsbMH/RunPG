package com.diploma.lilian.database.entity;

import com.diploma.lilian.game.data.CreatureData;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@DatabaseTable(tableName = "player")
public class Player implements CreatureData {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String playerName;

    @DatabaseField
    private String playerImage;

    @DatabaseField(columnName = "last_played", dataType = DataType.DATE)
    private Date lastPlayed;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Attributes attributes;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Backpack backpack;

    @ForeignCollectionField(eager = true)
    private Collection<SportActivity> sportActivities;

    public Player() {
    }

    public Player(int id, String playerName, String playerImage, Date lastPlayed, Collection<SportActivity> item, Attributes attributes, Backpack backpack) {
        this.id = id;
        this.playerName = playerName;
        this.playerImage = playerImage;
        this.lastPlayed = lastPlayed;
        this.sportActivities = item;
        this.attributes = attributes;
        this.backpack = backpack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    @Override
    public int getLevel() {
        return attributes.getLevel();
    }

    @Override
    public int getMaxHealthPoint() {
        return attributes.getMaxHealthPoint();
    }

    @Override
    public int getActualHealthPoint() {
        return attributes.getActualHealthPoint();
    }

    @Override
    public int getDamage() {
        return new Random().nextInt(20)+10;
    }

    @Override
    public void setActualHealthPoint(int actualHealthPoint) {
        attributes.setActualHealthPoint(actualHealthPoint);
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Collection<SportActivity> getSportActivities() {
        return sportActivities;
    }

    public void setSportActivities(Collection<SportActivity> sportActivities) {
        this.sportActivities = sportActivities;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", lastPlayed=" + lastPlayed +
                '}';
    }
}