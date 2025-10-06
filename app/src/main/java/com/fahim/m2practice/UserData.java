package com.fahim.m2practice;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserData {
    @PrimaryKey(autoGenerate = true)
    public int srno;
    public String Username;
    public String dateOfbirth;

    /*@NonNull
    @Override
    public String toString() {
        return "UserData{" +
                "srno=" + srno +
                ", Username='" + Username + '\'' +
                ", dateOfbirth='" + dateOfbirth + '\'' +
                '}';
    }*/
}
