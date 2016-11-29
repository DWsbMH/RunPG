package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "reward")
public class Reward {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String description;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SportActivity sportActivity;

    public Reward(String description, SportActivity sportActivity) {
        this.description = description;
        this.sportActivity = sportActivity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportActivity getSportActivity() {
        return sportActivity;
    }

    public void setSportActivity(SportActivity sportActivity) {
        this.sportActivity = sportActivity;
    }
}
