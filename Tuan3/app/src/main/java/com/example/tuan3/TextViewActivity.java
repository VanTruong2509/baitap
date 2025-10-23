package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
        TextView tv = findViewById(R.id.textViewExample);
        tv.setText("The quick brown fox jumps over the lazy dog.\nBold + Italic sample.");
    }
}
