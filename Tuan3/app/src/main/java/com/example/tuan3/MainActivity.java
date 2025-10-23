package com.example.tuan3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton(R.id.btnTextView, TextViewActivity.class);
        setButton(R.id.btnImageView, ImageViewActivity.class);
        setButton(R.id.btnEditText, EditTextActivity.class);
        setButton(R.id.btnButton, ButtonActivity.class);
        setButton(R.id.btnImageButton, ImageButtonActivity.class);
        setButton(R.id.btnToggleButton, ToggleButtonActivity.class);
        setButton(R.id.btnRadioButton, RadioButtonActivity.class);
        setButton(R.id.btnCheckBox, CheckBoxActivity.class);
        setButton(R.id.btnAutoComplete, AutoComplete.class);
        setButton(R.id.btnProgressBar, ProgressBarActivity.class);
        setButton(R.id.btnSpinner, SpinnerActivity.class);
        setButton(R.id.btnTimePicker, TimePickerActivity.class);
        setButton(R.id.btnDatePicker, DatePickerActivity.class);
        setButton(R.id.btnSeekBar, SeekBarActivity.class);
        setButton(R.id.btnAlertDialog, AlertDialogActivity.class);
        setButton(R.id.btnSwitch, SwitchActivity.class);
    }

    private void setButton(int id, Class<?> cls) {
        Button btn = findViewById(id);
        btn.setOnClickListener(v -> startActivity(new Intent(this, cls)));
    }
}
