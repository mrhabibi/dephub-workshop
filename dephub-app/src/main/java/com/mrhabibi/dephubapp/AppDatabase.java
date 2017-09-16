package com.mrhabibi.dephubapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by mrhabibi on 9/14/17.
 */
@Database(entities = {Pin.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PinDao pinDao();

}
