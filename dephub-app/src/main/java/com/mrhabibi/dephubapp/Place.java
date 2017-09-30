package com.mrhabibi.dephubapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by mrhabibi on 9/14/17.
 */
@Entity
public class Place implements Serializable {

    @ColumnInfo
    @PrimaryKey
    private long timestamp;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private double longitude;

    @ColumnInfo
    private double latitude;

    public Place(double longitude, double latitude) {
        this.name = "";
        this.description = "";
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
