package com.mrhabibi.dephubapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by mrhabibi on 9/14/17.
 */
@Dao
public interface PlaceDao {

    @Query("SELECT * FROM place ORDER BY timestamp DESC")
    List<Place> getAll();

    @Query("SELECT * FROM place WHERE name LIKE :query ORDER BY timestamp DESC")
    List<Place> getWithQuery(String query);

    @Insert
    void add(Place place);

    @Delete
    void delete(Place place);

    @Update
    void update(Place place);
}
