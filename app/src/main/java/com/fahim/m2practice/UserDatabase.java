package com.fahim.m2practice;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserData.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;

    public abstract UserDao userDao();

    public static UserDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

