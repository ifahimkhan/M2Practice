package com.fahim.m2practice;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    UserDatabase database;

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    EditText editTextText;
    Button select_date;
    Button submit;
    String date = "";

    //    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = UserDatabase.getInstance(this);
        Button submit = findViewById(R.id.submit);
        EditText editTextText = findViewById(R.id.editTextText);
        Button select_date = findViewById(R.id.select_date);
        Button delete = findViewById(R.id.delete);
        select_date.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(this);
            dialog.setOnDateSetListener((datePicker, year, month, day) -> date = day + "/" + (month + 1) + "/" + year);
            dialog.show();
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(() -> {
                    database.userDao().delete(Integer.parseInt(editTextText.getText().toString()));
                });
            }
        });

        submit.setOnClickListener(view -> {
            UserData userObject = new UserData();
            userObject.Username = editTextText.getText().toString();
            userObject.dateOfbirth = date;
            executorService.execute(() -> {
                database.userDao().insert(userObject);
            });
        });
        database.userDao().getAllUsers().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                StringBuilder result = new StringBuilder();
                for (UserData user : userData) {
                    result.append(user.srno + "-" + user.Username + "-" + user.dateOfbirth + "\n");
                }
                TextView textView = findViewById(R.id.result);
                textView.setText(result.toString());
            }
        });

    }

}