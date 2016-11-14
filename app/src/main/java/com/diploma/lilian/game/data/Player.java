package com.diploma.lilian.game.data;

import java.util.Random;

public class Player implements CreatureData {

    private final com.diploma.lilian.database.entity.Player player;
    private Random random;

    public Player(com.diploma.lilian.database.entity.Player player) {
        this.player = player;
        random = new Random();
    }

    @Override
    public int getLevel() {
        return player.getLevel();
    }

    @Override
    public int getMaxHealthPoint() {
        return player.getMaxHealthPoint();
    }

    @Override
    public int getActualHealthPoint() {
        return player.getActualHealthPoint();
    }

    @Override
    public int getDamage() {
        return random.nextInt(20)+10;
    }

    @Override
    public void setActualHealthPoint(int actualHealthPoint) {
        player.setActualHealthPoint(actualHealthPoint);
    }
}
