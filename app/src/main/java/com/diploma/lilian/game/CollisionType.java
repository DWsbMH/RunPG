package com.diploma.lilian.game;

public enum CollisionType {

    PLAYER_ENEMY(1), PLAYER_BUILDING(2), PLAYER_OTHER(3), INVALID(0);

    private final int value;

    CollisionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
