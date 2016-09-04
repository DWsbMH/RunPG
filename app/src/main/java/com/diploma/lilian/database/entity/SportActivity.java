package com.diploma.lilian.database.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "sport_activity")
public class SportActivity {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField(columnName = "src_id", unique = true) //
    private String sourceId;

    @DatabaseField(columnName = "activity_type")
    private String activityType;

    @DatabaseField(columnName = "start_time", dataType = DataType.DATE)
    private Date startTime;

    @DatabaseField(columnName = "duration")
    private int duration;

/*    @DatabaseField(columnName = "climb", canBeNull = false)
    private int climb;*/

    @DatabaseField(columnName = "utc_offset")
    private double utcOffset;

    @DatabaseField(columnName = "total_distance")
    private double totalDistance;

    @DatabaseField(columnName = "average_speed")
    private double averageSpeed;

    @DatabaseField(columnName = "max_speed")
    private double maxSpeed;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Player associatedPlayer;

    public SportActivity() {
    }

    public SportActivity(String sourceId, String activityType, Date startTime, int duration/*, int climb*/, double utcOffset, double totalDistance, double averageSpeed, double maxSpeed) {
        this.sourceId = sourceId;
        this.activityType = activityType;
        this.startTime = startTime;
        this.duration = duration;
//        this.climb = climb;
        this.utcOffset = utcOffset;
        this.totalDistance = totalDistance;
        this.averageSpeed = averageSpeed;
        this.maxSpeed = maxSpeed;
    }

/*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
*/

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

/*    public int getClimb() {
        return climb;
    }

    public void setClimb(int climb) {
        this.climb = climb;
    }*/

    public double getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(double utcOffset) {
        this.utcOffset = utcOffset;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Player getAssociatedPlayer() {
        return associatedPlayer;
    }

    public void setAssociatedPlayer(Player associatedPlayer) {
        this.associatedPlayer = associatedPlayer;
    }
}
