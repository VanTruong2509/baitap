package com.example.tuan2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;

public class NumberActivity extends AppCompatActivity {
    EditText edtSo;
    Button btnTao;
    LinearLayout layoutDanhSach;
    TextView txtThongBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        edtSo = findViewById(R.id.edtSo);
        btnTao = findViewById(R.id.btnTao);
        layoutDanhSach = findViewById(R.id.layoutDanhSach);
        txtThongBao = findViewById(R.id.txtThongBao);

        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutDanhSach.removeAllViews();
                String soNhap = edtSo.getText().toString().trim();

                if (soNhap.isEmpty()) {
                    txtThongBao.setText("Dữ liệu bạn nhập không hợp lệ");
                    return;
                }

                try {
                    int n = Integer.parseInt(soNhap);
                    txtThongBao.setText("");

                    for (int i = 1; i <= n; i++) {
                        TextView tv = new TextView(NumberActivity.this);
                        tv.setText(String.valueOf(i));
                        tv.setTextSize(18);
                        tv.setTextColor(Color.WHITE);
                        tv.setGravity(Gravity.CENTER);
                        tv.setBackgroundColor(Color.parseColor("#F44336"));
                        tv.setPadding(0, 20, 0, 20);

                        LinearLayout.LayoutParams params =
                                new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 10, 0, 0);
                        tv.setLayoutParams(params);

                        layoutDanhSach.addView(tv);
                    }

                } catch (NumberFormatException e) {
                    txtThongBao.setText("Dữ liệu bạn nhập không hợp lệ");
                }
            }
        });
    }
}
