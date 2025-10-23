package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class ImageButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagebutton);
        ImageButton ib = findViewById(R.id.imageButtonSample);
        ib.setOnClickListener(v -> Toast.makeText(this, "ImageButton clicked", Toast.LENGTH_SHORT).show());
    }
}
