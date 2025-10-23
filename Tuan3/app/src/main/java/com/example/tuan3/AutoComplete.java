package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.Button;

public class AutoComplete extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);

        AutoCompleteTextView actv = findViewById(R.id.autoCompleteSample);
        String[] items = {"Vietnam", "USA", "UK", "France", "Japan", "China"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        actv.setAdapter(adapter);

        Button btn = findViewById(R.id.btnAutoOk);
        btn.setOnClickListener(v -> Toast.makeText(this, "Selected: " + actv.getText().toString(), Toast.LENGTH_SHORT).show());
    }
}
