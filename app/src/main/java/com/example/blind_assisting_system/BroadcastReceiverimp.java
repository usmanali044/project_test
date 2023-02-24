package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BroadcastReceiverimp extends AppCompatActivity {

    private BroadcastReceiver broad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
            pb.setProgress(level);
            TextView tv = (TextView) findViewById(R.id.tv);
            tv.setText("Battery Level = " + level);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiverimp);
        registerReceiver(broad, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}