package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpass extends AppCompatActivity {

    FirebaseAuth auth;
    EditText remail;
    Button r_btn;
    ProgressBar prog_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);

        remail = (EditText) findViewById(R.id.reset_email);
        r_btn = (Button) findViewById(R.id.resetbtn);
        prog_bar = (ProgressBar) findViewById(R.id.progbar);

        r_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = remail.getText().toString();
                prog_bar.setVisibility(View.VISIBLE);

                if(Email.isEmpty()){
                    remail.setError("Email Is Missing");
                    remail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                    remail.setError("Invalid Email Address");
                    remail.requestFocus();
                    return;
                }
//                Toast.makeText(resetpass.this, Email, Toast.LENGTH_LONG).show();

                auth.getInstance().sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            prog_bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(resetpass.this, "Reset Link Has Been Sent To Your Mail, Kindly Check", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(resetpass.this, LogIn.class));

                        }else{
                            prog_bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(resetpass.this, "Unable to send Reset Link, ERROR!!!!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(resetpass.this, LogIn.class));
                        }
                    }
                });
            }
        });
    }
}