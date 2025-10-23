package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiobutton);

        RadioGroup rg = findViewById(R.id.radioGroupSample);
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            String choice = "";
            if (checkedId == R.id.rbOpt1) choice = "Option 1";
            else if (checkedId == R.id.rbOpt2) choice = "Option 2";
            else if (checkedId == R.id.rbOpt3) choice = "Option 3";
            Toast.makeText(this, "Selected: " + choice, Toast.LENGTH_SHORT).show();
        });
    }
}
