package com.diploma.lilian.game.data;

public interface CreatureData {

    int getLevel();
    int getMaxHealthPoint();
    int getActualHealthPoint();
    int getDamage();
    void setActualHealthPoint(int actualHealthPoint);
    int getId();
}
