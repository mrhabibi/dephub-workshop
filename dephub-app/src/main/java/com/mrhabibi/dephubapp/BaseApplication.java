package com.mrhabibi.dephubapp;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by mrhabibi on 9/16/17.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication get() {
        return instance;
    }

    AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "app-database.db").allowMainThreadQueries().build();
    }
}
