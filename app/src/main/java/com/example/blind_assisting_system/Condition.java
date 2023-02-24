package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Condition extends AppCompatActivity {
    TextView tv;
    Button bt;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);

        tv = (TextView) findViewById(R.id.result);
        bt = (Button) findViewById(R.id.click);
        et = (EditText) findViewById(R.id.et);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String option = et.getText().toString();
                if(option.equals("Pass")){
                    tv.setText("Congrats You Are Passed");
                }else if(option.equals("Fail")){
                    tv.setText("Sorry you are Failed....");
                }else{
                    tv.setText("Invaid Argument");
                }

            }
        });


    }
}