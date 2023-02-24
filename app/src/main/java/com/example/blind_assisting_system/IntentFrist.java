package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IntentFrist extends AppCompatActivity {
    EditText fet,set;
    Button btn;
    String name, age;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_frist);

        fet = (EditText) findViewById(R.id.fet);
        set = (EditText) findViewById(R.id.set);
        btn = (Button) findViewById(R.id.submit);
        tv = (TextView) findViewById(R.id.tvcounter);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = fet.getText().toString();
                age = set.getText().toString();
                Intent i = new Intent(IntentFrist.this, IntentSecond.class);
                i.putExtra("Name", name);
                i.putExtra("Age", age);
                startActivity(i);



            }
        });

    }
}