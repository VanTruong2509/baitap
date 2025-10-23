package com.example.tuan2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class Bai1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        EditText edtTen = findViewById(R.id.edtTen);
        EditText edtTuoi = findViewById(R.id.edtTuoi);
        Button btnKiemTra = findViewById(R.id.btnKiemTra);
        TextView txtKetQua = findViewById(R.id.txtKetQua);

        btnKiemTra.setOnClickListener(v -> {
            String ten = edtTen.getText().toString();
            String tuoiStr = edtTuoi.getText().toString();

            if (ten.isEmpty() || tuoiStr.isEmpty()) {
                txtKetQua.setText("Vui lòng nhập đầy đủ thông tin");
                return;
            }

            try {
                int tuoi = Integer.parseInt(tuoiStr);
                String loai;

                if (tuoi > 65) loai = "Người già";
                else if (tuoi >= 6) loai = "Người lớn";
                else if (tuoi >= 2) loai = "Trẻ em";
                else loai = "Em bé";

                txtKetQua.setText(ten + " là " + loai);
            } catch (NumberFormatException e) {
                txtKetQua.setText("Tuổi phải là số hợp lệ");
            }
        });
    }
}
