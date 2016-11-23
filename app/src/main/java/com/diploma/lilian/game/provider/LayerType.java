package com.diploma.lilian.game.provider;

public enum LayerType {

    MAIN_LAYER(1), GROUND_LAYER(2), SHADOW_LAYER(3), INVALID_LAYER(-1);

    private final int value;

    LayerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
