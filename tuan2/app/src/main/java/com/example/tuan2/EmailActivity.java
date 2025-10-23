package com.example.tuan2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class EmailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        EditText edtEmail = findViewById(R.id.edtEmail);
        Button btnKiemTra = findViewById(R.id.btnKiemTra);
        TextView txtThongBao = findViewById(R.id.txtThongBao);

        btnKiemTra.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();

            if (email.isEmpty()) {
                txtThongBao.setText("Email không hợp lệ");
            } else if (!email.contains("@")) {
                txtThongBao.setText("Email không đúng định dạng");
            } else {
                txtThongBao.setText("Bạn đã nhập email hợp lệ");
            }
        });
    }
}
