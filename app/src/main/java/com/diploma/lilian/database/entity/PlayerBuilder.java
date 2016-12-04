package com.diploma.lilian.database.entity;

import java.util.Date;

public class PlayerBuilder {
    private int id;
    private String playerName;
    private String playerImage;
    private Date lastPlayed;
    private Backpack backpack;
    private Attributes attributes;
    private PlayerSheet playerSheet;

    public PlayerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public PlayerBuilder setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public PlayerBuilder setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
        return this;
    }

    public PlayerBuilder setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
        return this;
    }

    public PlayerBuilder setBackpack(Backpack backpack) {
        this.backpack = backpack;
        return this;
    }

    public PlayerBuilder setAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }

    public PlayerBuilder setPlayerSheet(PlayerSheet playerSheet) {
        this.playerSheet = playerSheet;
        return this;
    }

    public Player createPlayer() {
        return new Player(id, playerName, playerImage, lastPlayed, attributes, backpack, playerSheet);
    }
}