package com.example.blind_assisting_system;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Thread tr = new Thread(){

            public void run(){
                try {
                    sleep(2000);
                }catch (Exception ex){
                    ex.fillInStackTrace();
                }finally {
                    Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                    startActivity(i);
                }
            }
        };tr.start();
    }
}