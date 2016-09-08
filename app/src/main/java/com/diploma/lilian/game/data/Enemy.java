package com.diploma.lilian.game.data;

import java.util.Random;

public class Enemy implements CreatureData{

    private final int level;
    private Random random;

    public Enemy(int level) {
        this.random = new Random();
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxHealthPoint() {
        return getLevel()*15;
    }

    @Override
    public int getActualHealthPoint() {
        return getMaxHealthPoint();
    }

    @Override
    public int getDamage() {
        return getLevel()*(random.nextInt(20)+1);
    }
}
