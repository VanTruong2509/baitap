package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ImageView iv = findViewById(R.id.imageViewSample);
        iv.setImageResource(R.mipmap.ic_launcher); // change to drawable if you add image
    }
}
