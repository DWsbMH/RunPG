package com.diploma.lilian.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tracker_service")
public class TrackerService implements Parcelable {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField(defaultValue = "false")
    private boolean connected;

    @DatabaseField
    private String access_token;

    public TrackerService() {
    }

    public TrackerService(String name) {
        this.name = name;
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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackerService that = (TrackerService) o;

        return id==that.id;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (connected ? 1 : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.connected ? (byte) 1 : (byte) 0);
        dest.writeString(this.access_token);
    }

    protected TrackerService(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.connected = in.readByte() != 0;
        this.access_token = in.readString();
    }

    public static final Parcelable.Creator<TrackerService> CREATOR = new Parcelable.Creator<TrackerService>() {
        @Override
        public TrackerService createFromParcel(Parcel source) {
            return new TrackerService(source);
        }

        @Override
        public TrackerService[] newArray(int size) {
            return new TrackerService[size];
        }
    };
}
