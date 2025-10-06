package com.fahim.m2practice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserData userObject);
    @Query("SELECT * FROM UserData")
    LiveData<List<UserData>> getAllUsers();
}
