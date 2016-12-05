package com.diploma.lilian.database.entity;

import com.diploma.lilian.game.data.CreatureData;
import com.diploma.lilian.game.util.Formulas;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Random;

@DatabaseTable(tableName = "player")
public class Player implements CreatureData {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String playerName;

    @DatabaseField(defaultValue = "0")
    private int gold;

    @DatabaseField
    private String playerImage;

    @DatabaseField(columnName = "last_played", dataType = DataType.DATE)
    private Date lastPlayed;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Attributes attributes;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Backpack backpack;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private PlayerSheet playerSheet;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<SportActivity> sportActivities;

    @DatabaseField
    private String lastScene;

    @DatabaseField
    private float lastXPosition;

    @DatabaseField
    private float lastYPosition;

    public Player() {
    }

    public Player(int id, String playerName, String playerImage, Date lastPlayed, Attributes attributes, Backpack backpack, PlayerSheet playerSheet) {
        this.id = id;
        this.playerName = playerName;
        this.playerImage = playerImage;
        this.lastPlayed = lastPlayed;
        this.attributes = attributes;
        this.backpack = backpack;
        this.playerSheet = playerSheet;
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
        int maxHealth = Formulas.getMaxHealth(this);
        if(playerSheet.getEndurance() != null){
            maxHealth *= playerSheet.getEndurance().getEffect().getEffect();
        }
        return maxHealth;
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

    public ForeignCollection<SportActivity> getSportActivities() {
        return sportActivities;
    }

    public void setSportActivities(ForeignCollection<SportActivity> sportActivities) {
        this.sportActivities = sportActivities;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public PlayerSheet getPlayerSheet() {
        return playerSheet;
    }

    public void setPlayerSheet(PlayerSheet playerSheet) {
        this.playerSheet = playerSheet;
    }

    public String getLastScene() {
        return lastScene;
    }

    public void setLastScene(String lastScene) {
        this.lastScene = lastScene;
    }

    public float getLastXPosition() {
        return lastXPosition;
    }

    public void setLastXPosition(float lastXPosition) {
        this.lastXPosition = lastXPosition;
    }

    public float getLastYPosition() {
        return lastYPosition;
    }

    public void setLastYPosition(float lastYPosition) {
        this.lastYPosition = lastYPosition;
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