package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;

public abstract class Item {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Backpack backpack;

    @DatabaseField
    private String imageName;

    Item() {
    }

    Item(Backpack backpack, String name, String imageName) {
        this.backpack = backpack;
        this.name = name;
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
