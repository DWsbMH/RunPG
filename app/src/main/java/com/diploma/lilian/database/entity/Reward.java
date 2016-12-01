package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "reward")
public class Reward {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField
    private String what;
    @DatabaseField
    private String whatFor;
    @DatabaseField
    private String when;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private SportActivity sportActivity;

    public Reward() {
    }

    public Reward(String what, String whatFor, String when, SportActivity sportActivity) {
        this.what = what;
        this.whatFor = whatFor;
        this.when = when;
        this.sportActivity = sportActivity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhatFor() {
        return whatFor;
    }

    public void setWhatFor(String whatFor) {
        this.whatFor = whatFor;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public SportActivity getSportActivity() {
        return sportActivity;
    }

    public void setSportActivity(SportActivity sportActivity) {
        this.sportActivity = sportActivity;
    }
}
