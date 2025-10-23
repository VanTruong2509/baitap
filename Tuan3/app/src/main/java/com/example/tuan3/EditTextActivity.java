package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditTextActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);

        EditText et = findViewById(R.id.editTextSample);
        Button btn = findViewById(R.id.btnSubmitEdit);
        TextView tv = findViewById(R.id.tvResultEdit);

        btn.setOnClickListener(v -> {
            String s = et.getText().toString();
            if (s.isEmpty()) Toast.makeText(this, "Please type something", Toast.LENGTH_SHORT).show();
            tv.setText("Bạn nhập: " + s);
        });
    }
}
