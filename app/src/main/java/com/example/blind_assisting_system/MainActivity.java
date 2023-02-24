package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv, tv1;
    Button btncounter,btnreset;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tvcounter);
        tv1 = (TextView) findViewById(R.id.tvcounter1);
        btncounter = (Button) findViewById(R.id.btncounter);
        btnreset = (Button) findViewById(R.id.btnreset);

        btncounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                tv1.setText("Counter = "+counter);
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                tv1.setText("Counter = "+counter);
            }
        });
    }
}