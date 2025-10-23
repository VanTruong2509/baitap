package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;

public class TimePickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);

        TimePicker tp = findViewById(R.id.timePickerSample);
        Button btn = findViewById(R.id.btnGetTime);
        btn.setOnClickListener(v -> {
            int h = tp.getHour();
            int m = tp.getMinute();
            Toast.makeText(this, String.format(Locale.getDefault(), "Time: %02d:%02d", h, m), Toast.LENGTH_SHORT).show();
        });
    }
}
