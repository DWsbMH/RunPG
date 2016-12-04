package com.diploma.lilian.database.entity;

public enum PotionEffect {

    SMALL(1.1, 2), MEDIUM(1.15, 4), LARGE(1.25, 6);

    private final double effect;
    private final int durability;

    PotionEffect(double effect, int durability) {
        this.effect = effect;
        this.durability = durability;
    }

    public double getEffect() {
        return effect;
    }

    public int getDurability() {
        return durability;
    }
}
