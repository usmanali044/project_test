package com.example.blind_assisting_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Admin_Dashboard extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    String userId;
    TextView name, email, code;
    Button logout, add_code, users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        logout = (Button) findViewById(R.id.logout);
        add_code = (Button) findViewById(R.id.add_code);
        users = (Button) findViewById(R.id.users);

//        SignOut Button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Admin_Dashboard.this, LogIn.class));
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashboard.this,list_user.class);
                startActivity(intent);
            }
        });

        add_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Dashboard.this, admin_add_code.class);
                startActivity(intent);
            }
        });
    }
}