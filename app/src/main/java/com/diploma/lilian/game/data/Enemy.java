package com.diploma.lilian.game.data;

import java.util.Random;

public class Enemy implements CreatureData{

    private final int level;
    private Random random;
    private int actualHealthPoint;
    private int maxHealthPoint;

    public Enemy(int level) {
        this.random = new Random();
        this.level = level;
        this.maxHealthPoint = getLevel()*15*(1+100/(random.nextInt(100)+1));
        this.actualHealthPoint = this.maxHealthPoint;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    @Override
    public int getActualHealthPoint() {
        return actualHealthPoint;
    }

    @Override
    public int getDamage() {
        return getLevel()*(random.nextInt(20)+1);
    }

    @Override
    public void setActualHealthPoint(int actualHealthPoint) {
        this.actualHealthPoint = actualHealthPoint;
    }
}
