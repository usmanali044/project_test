package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IntentSecond extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_second);
        tv = (TextView) findViewById(R.id.tvreceiver);

        Intent it = getIntent();
        String name = it.getStringExtra("Name");
        String age = it.getStringExtra("Age");

        tv.setText("Your Name Is : "+name+"Age : "+age);
    }
}