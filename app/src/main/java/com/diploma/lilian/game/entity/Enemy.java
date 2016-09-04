package com.diploma.lilian.game.entity;


import com.diploma.lilian.engine.IsoSprite;

/**
 * Created by Lilian on 2016. 08. 25..
 */
public class Enemy {
    private IsoSprite sprite;

    public Enemy(IsoSprite mushroom) {

        this.sprite = mushroom;
    }

    public IsoSprite getSprite() {
        return sprite;
    }
}
