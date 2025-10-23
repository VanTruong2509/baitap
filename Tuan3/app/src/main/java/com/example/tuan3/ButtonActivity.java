package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.graphics.Color;

public class ButtonActivity extends AppCompatActivity {
    private boolean toggled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button btn = findViewById(R.id.btnColor);
        LinearLayout root = findViewById(R.id.rootButtonActivity);

        btn.setOnClickListener(v -> {
            toggled = !toggled;
            root.setBackgroundColor(toggled ? Color.parseColor("#FFEB3B") : Color.WHITE);
            btn.setText(toggled ? "Toggled ON" : "Toggle color");
        });
    }
}
