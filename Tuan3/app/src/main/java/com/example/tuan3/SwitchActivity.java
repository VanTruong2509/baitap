package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

public class SwitchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Switch sw = findViewById(R.id.switchSample);
        TextView tv = findViewById(R.id.tvSwitchState);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> tv.setText(isChecked ? "Switch is ON" : "Switch is OFF"));
    }
}
