package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;

    ImageView app_name;
    TextView login;
    EditText name, email,pass,code;
    Button register_btn;
    ProgressBar progress_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        app_name = (ImageView) findViewById(R.id.appname);
        name =  (EditText) findViewById(R.id.Rname);
        email = (EditText) findViewById(R.id.Remail);
        pass = (EditText) findViewById(R.id.Rpass);
        code = (EditText) findViewById(R.id.Rcode);
        progress_bar = (ProgressBar) findViewById(R.id.progbar);
        register_btn = (Button) findViewById(R.id.Rbtn);
        login = (TextView) findViewById(R.id.login);

        register_btn.setOnClickListener(this);
        app_name.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.appname:
                startActivity(new Intent(this, MainActivity2.class));
                break;
            case R.id.login:
                startActivity(new Intent(this, LogIn.class));
                break;
            case R.id.Rbtn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String u_name= name.getText().toString();
        String u_email= email.getText().toString();
        String u_pass= pass.getText().toString();
        String u_code= code.getText().toString();


        if(u_name.isEmpty()){
            name.setError("Full Name Is Required");
            name.requestFocus();
            return;
        }
        if(u_email.isEmpty()){
            email.setError("Full Name Is Required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(u_email).matches()){
            email.setError("Invalid Email Address");
            email.requestFocus();
            return;
        }
        if(u_code.isEmpty()){
            code.setError("Full Name Is Required");
            code.requestFocus();
            return;
        }
        if(u_pass.isEmpty()){
            pass.setError("Full Name Is Required");
            pass.requestFocus();
            return;
        }
        if(u_pass.length() < 6){
            pass.setError("minimum 6 character password required");
            pass.requestFocus();
            return;
        }


        progress_bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(u_email, u_pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(RegisterUser.this, "User Has Been Registered Successfully", Toast.LENGTH_LONG).show();
                            progress_bar.setVisibility(View.INVISIBLE);

                            User user = new User(u_name,u_email,u_code, u_pass);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterUser.this, "User Has Been Registered Successfully", Toast.LENGTH_LONG).show();
                                                progress_bar.setVisibility(View.INVISIBLE);
                                                Intent intent = new Intent(RegisterUser.this, LogIn.class);
                                                startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(RegisterUser.this, "Sorry! Unable To Register!", Toast.LENGTH_LONG).show();
                                                progress_bar.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(RegisterUser.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                            progress_bar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}