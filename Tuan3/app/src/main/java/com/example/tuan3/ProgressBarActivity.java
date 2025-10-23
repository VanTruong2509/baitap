package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.os.Handler;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar pb;
    private Handler handler = new Handler();
    private int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        pb = findViewById(R.id.progressBarSample);
        Button btn = findViewById(R.id.btnStartProgress);
        btn.setOnClickListener(v -> {
            progress = 0;
            pb.setProgress(0);
            handler.post(progressRunnable);
        });
    }

    private final Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            progress += 5;
            pb.setProgress(progress);
            if (progress < pb.getMax()) handler.postDelayed(this, 200);
        }
    };
}
