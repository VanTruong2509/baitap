package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog);

        Button btn = findViewById(R.id.btnShowAlert);
        btn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm")
                    .setMessage("Do you want to continue?")
                    .setPositiveButton("Yes", (dialog, which) -> Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show())
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }
}
