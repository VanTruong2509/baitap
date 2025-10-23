package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);

        CheckBox cb1 = findViewById(R.id.cb1);
        CheckBox cb2 = findViewById(R.id.cb2);
        CheckBox cb3 = findViewById(R.id.cb3);
        Button btn = findViewById(R.id.btnCheckResult);

        btn.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder("Selected: ");
            if (cb1.isChecked()) sb.append("A ");
            if (cb2.isChecked()) sb.append("B ");
            if (cb3.isChecked()) sb.append("C ");
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        });
    }
}
