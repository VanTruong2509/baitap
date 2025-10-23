package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ToggleButton;
import android.widget.TextView;

public class ToggleButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togglebutton);

        ToggleButton tb = findViewById(R.id.toggleSample);
        TextView tv = findViewById(R.id.tvToggleState);
        tb.setOnCheckedChangeListener((buttonView, isChecked) -> tv.setText(isChecked ? "ON" : "OFF"));
    }
}
