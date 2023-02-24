package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class Calculator extends AppCompatActivity implements View.OnClickListener{
    Button btnp,btnm, btnmul, btnd;
    TextView result;
    EditText et1, et2;
    int a,b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        result = (TextView) findViewById(R.id.result);

        btnp = (Button) findViewById(R.id.btnp);
        btnm = (Button) findViewById(R.id.btnm);
        btnmul = (Button) findViewById(R.id.btnmul);
        btnd = (Button) findViewById(R.id.btnd);

//        btnp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                a = Integer.valueOf(et1.getText().toString());
//                b = Integer.valueOf(et2.getText().toString());
//                c = a+b;
//                result.setText("Result = "+c);
//            }
//        });
//
//        btnm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                a = Integer.valueOf(et1.getText().toString());
//                b = Integer.valueOf(et2.getText().toString());
//                c = a-b;
//                result.setText("Result = "+c);
//            }
//        });
//
//        btnmul.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                a = Integer.valueOf(et1.getText().toString());
//                b = Integer.valueOf(et2.getText().toString());
//                c = a*b;
//                result.setText("Result = "+c);
//            }
//        });
//
//        btnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                a = Integer.valueOf(et1.getText().toString());
//                b = Integer.valueOf(et2.getText().toString());
//                c = a/b;
//                result.setText("Result = "+c);
//            }
//        });

        btnp.setOnClickListener(this);
        btnm.setOnClickListener(this);
        btnmul.setOnClickListener(this);
        btnd.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnp:
                a = Integer.valueOf(et1.getText().toString());
                b = Integer.valueOf(et2.getText().toString());
                c = a+b;
                result.setText("Result = "+c);
                break;

            case R.id.btnm:
                a = Integer.valueOf(et1.getText().toString());
                b = Integer.valueOf(et2.getText().toString());
                c = a-b;
                result.setText("Result = "+c);
                break;

            case R.id.btnmul:
                a = Integer.valueOf(et1.getText().toString());
                b = Integer.valueOf(et2.getText().toString());
                c = a*b;
                result.setText("Result = "+c);
                break;

            case R.id.btnd:
                a = Integer.valueOf(et1.getText().toString());
                b = Integer.valueOf(et2.getText().toString());
                c = a/b;
                result.setText("Result = "+c);
                break;
        }
    }
}