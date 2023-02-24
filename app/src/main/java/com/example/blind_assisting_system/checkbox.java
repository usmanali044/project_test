package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class checkbox extends AppCompatActivity {
    CheckBox ch1, ch2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);

        ch1 = (CheckBox) findViewById(R.id.check1);
        ch2 = (CheckBox) findViewById(R.id.check2);

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ch1.isChecked()) {
                    Toast.makeText(checkbox.this, "Male", Toast.LENGTH_LONG).show();
                }
            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ch2.isChecked()) {
                    Toast.makeText(checkbox.this, "Female", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}