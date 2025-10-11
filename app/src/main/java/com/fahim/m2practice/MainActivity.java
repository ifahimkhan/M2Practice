package com.fahim.m2practice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String dateString = "";
    String timeString = "";
    InternalStorage internalStorage = new InternalStorage();
    SharedPreferencesStorage sharedPreferencesStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferencesStorage = new SharedPreferencesStorage(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button datePicker = findViewById(R.id.date_picker);
        Button timePicker = findViewById(R.id.time_picker);
        Button write = findViewById(R.id.write);
        Button read = findViewById(R.id.read);
        TextView result = findViewById(R.id.textView);


        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this);
                datePickerDialog.setOnDateSetListener((datePicker1, i, i1, i2) -> dateString = i + "/" + (i1 + 1) + "/" + i2);
                datePickerDialog.show();

            }
        });

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, (timePicker1, i, i1) -> timeString = i + ":" + i1, 0, 0, true);
                timePickerDialog.show();

            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = dateString + "\n" + timeString;
//                internalStorage.writeToFile(MainActivity.this, result);
                sharedPreferencesStorage.saveUserData(result);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result.setText(internalStorage.readFromFile(MainActivity.this));
                result.setText(sharedPreferencesStorage.fetchUserData());
            }
        });
    }

}