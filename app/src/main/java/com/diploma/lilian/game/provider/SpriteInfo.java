package com.diploma.lilian.game.provider;

import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.game.data.CreatureData;

public class SpriteInfo {

    private IsoSprite sprite;
    private int layerType;
    private CreatureData data;

    public SpriteInfo(IsoSprite sprite, int layerType, CreatureData data) {
        this.sprite = sprite;
        this.layerType = layerType;
        this.data = data;
    }

    public SpriteInfo(IsoSprite sprite, int layerType) {
        this.sprite = sprite;
        this.layerType = layerType;
        this.data = null;
    }

    public IsoSprite getSprite() {
        return sprite;
    }

    public int getLayerType() {
        return layerType;
    }

    public CreatureData getData() {
        return data;
    }
}
