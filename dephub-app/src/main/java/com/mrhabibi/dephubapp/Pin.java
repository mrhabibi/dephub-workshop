package com.mrhabibi.dephubapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by mrhabibi on 9/14/17.
 */
@Entity
public class Pin {

    @PrimaryKey
    private int uid;

    @ColumnInfo
    private long name;

    @ColumnInfo
    private long longitude;

    @ColumnInfo
    private long latitude;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getName() {
        return name;
    }

    public void setName(long name) {
        this.name = name;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
