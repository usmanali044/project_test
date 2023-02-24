package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadoiButton extends AppCompatActivity {

    RadioGroup radio;
    RadioButton rdbtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radoi_button);

        radio = (RadioGroup) findViewById(R.id.rdgroup);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rdbtn1 = (RadioButton) findViewById(R.id.rdbtn1);
                if(rdbtn1.isChecked()){
                    Toast.makeText(RadoiButton.this, "Male", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RadoiButton.this, "Female", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}