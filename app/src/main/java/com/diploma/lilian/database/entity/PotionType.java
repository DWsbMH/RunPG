package com.diploma.lilian.database.entity;

public enum PotionType {

    HEALTH("health"), LUCK("luck"), STRENGTH("strength"), ENDURANCE("endurance");

    private final String type;

    PotionType(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }

}
