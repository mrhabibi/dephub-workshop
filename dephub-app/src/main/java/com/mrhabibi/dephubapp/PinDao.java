package com.mrhabibi.dephubapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by mrhabibi on 9/14/17.
 */
@Dao
public interface PinDao {

    @Query("SELECT * FROM pin ORDER BY timestamp DESC")
    List<Pin> getAll();

    @Insert
    void add(Pin pin);

    @Delete
    void delete(Pin pin);
}
