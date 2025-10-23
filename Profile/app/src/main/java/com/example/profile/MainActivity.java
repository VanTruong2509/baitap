package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtName = findViewById(R.id.txtName);
        TextView txtLocation = findViewById(R.id.txtLocation);

        txtName.setText("Nguyễn Văn Trường");
        txtLocation.setText("Phường Vũng Tàu,TP Hồ Chí Minh");
    }
}
