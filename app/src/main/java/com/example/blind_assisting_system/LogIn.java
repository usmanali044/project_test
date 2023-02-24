package com.example.blind_assisting_system;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity implements View.OnClickListener{
    TextView register, fpass;
    Button lbtn;
    EditText ed1,ed2;
    ProgressBar prog_bar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();

        ed1 = (EditText) findViewById(R.id.lemail);
        ed2 = (EditText) findViewById(R.id.lpass);

        register = (TextView) findViewById(R.id.lregister);
        fpass = (TextView) findViewById(R.id.fpass);
        lbtn = (Button) findViewById(R.id.lbtn);
        prog_bar = (ProgressBar) findViewById(R.id.progbar);

        register.setOnClickListener(this);
        lbtn.setOnClickListener(this);
        fpass.setOnClickListener(this);

    }

    //    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lregister:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.fpass:
                startActivity(new Intent(this, resetpass.class));
                break;
            case R.id.lbtn:
                login();
                break;
        }
    }

    private void login() {
        String email = ed1.getText().toString();
        String pass = ed2.getText().toString();

        if(email.isEmpty()){
            ed1.setError("Email is missing!");
            ed1.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            ed2.setError("Password is missing!");
            ed2.requestFocus();
            return;
        }

        prog_bar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            prog_bar.setVisibility(View.INVISIBLE);

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            Email Verification
                            if(user.isEmailVerified()){
                                Toast.makeText(LogIn.this, "LogedIn Successful", Toast.LENGTH_LONG).show();

                                if("uaciit@gmail.com".equals(email)){
                                    Intent intent = new Intent(LogIn.this, Admin_Dashboard.class);
                                    startActivity(intent);
                                }else{
                                    Intent intent = new Intent(LogIn.this, Dashboard.class);
                                    startActivity(intent);
                                }

                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(LogIn.this, "Verification mail has been sent, kindly verify account!", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            prog_bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LogIn.this, "Invalid Email Or Password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}