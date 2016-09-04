package com.diploma.lilian.game.entity;


import com.diploma.lilian.engine.IsoSprite;

public class Player {

    private IsoSprite sprite;
    private com.diploma.lilian.database.entity.Player data;

    public Player() {
        this.data = data;
    }

    public IsoSprite getSprite() {
        return sprite;
    }

    public void setSprite(IsoSprite sprite) {
        this.sprite = sprite;
    }

    public com.diploma.lilian.database.entity.Player getData() {
        return data;
    }

    public void setData(com.diploma.lilian.database.entity.Player data) {
        this.data = data;
    }
}
