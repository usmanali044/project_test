package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    String userId;
    TextView name, email, code;
    Button logout,ocr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        name = (TextView) findViewById(R.id.dname);
        email = (TextView) findViewById(R.id.demail);
        code = (TextView) findViewById(R.id.dcode);
        logout = (Button) findViewById(R.id.logout);
        ocr = (Button) findViewById(R.id.ocr);

//        SignOut Button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, LogIn.class));
            }
        });

//        Reading Text From Image
        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Rec_txt.class);
                startActivity(intent);
            }
        });


//        Data Showing On Dashboard
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);

                if(userprofile != null){
                    String u_name = userprofile.name;
                    String u_email = userprofile.email;
                    String u_code = userprofile.code;

                    name.setText("Name : "+u_name);
                    email.setText("Email : "+u_email);
                    code.setText("Code : "+u_code);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dashboard.this, "Sorry something went wrong", Toast.LENGTH_LONG).show();
            }
        });

    }
}