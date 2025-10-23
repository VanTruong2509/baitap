package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;

public class DatePickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        DatePicker dp = findViewById(R.id.datePickerSample);
        Button btn = findViewById(R.id.btnGetDate);
        btn.setOnClickListener(v -> {
            int y = dp.getYear();
            int m = dp.getMonth() + 1;
            int d = dp.getDayOfMonth();
            Toast.makeText(this, String.format(Locale.getDefault(), "Date: %04d-%02d-%02d", y, m, d), Toast.LENGTH_SHORT).show();
        });
    }
}
