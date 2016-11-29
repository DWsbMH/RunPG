package com.diploma.lilian.tracker;

public enum SportActivityType {

    RUNNING("Running"),HIKING("Hiking"), CROSSFIT("CrossFit"),
    CYCLING("Cycling"),YOGA("Yoga"),SNOWBOARDING("Snowboarding"),
    WALKING("Walking"),SWIMMING("Swimming"),SKATING("Skating"),
    OTHER("OTHER");

    private final String type;

    SportActivityType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
