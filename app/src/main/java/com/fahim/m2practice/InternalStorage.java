package com.fahim.m2practice;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InternalStorage {
    public void writeToFile(Context context, String data) {

        String fileName = "file.txt";
        try {

            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(context, "write success", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String readFromFile(Context context) {

        String fileName = "file.txt";
        StringBuilder stringBuilder = new StringBuilder();

        try {

            FileInputStream fileInputStream = context.openFileInput(fileName);
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                stringBuilder.append((char) i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
