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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class admin_add_code extends AppCompatActivity {


    FirebaseAuth auth;
    EditText e_code;
    Button btn_code;
    ProgressBar prog_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_code);
//
        e_code = (EditText) findViewById(R.id.cet);
        btn_code = (Button) findViewById(R.id.cbtn);
        prog_bar = (ProgressBar) findViewById(R.id.reset_progbar);
        add_code a_code = new add_code();

        btn_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Cd = e_code.getText().toString();
                prog_bar.setVisibility(View.VISIBLE);

                if(Cd.isEmpty()){
                    e_code.setError("Code Is Missing");
                    e_code.requestFocus();
                    return;
                }

                Code code = new Code(Cd);

                FirebaseDatabase.getInstance().getReference("Code")
                        .setValue(code)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(admin_add_code.this, "Code Has Been Added Successfully", Toast.LENGTH_LONG).show();
                                    prog_bar.setVisibility(View.INVISIBLE);
                                    Intent intent = new Intent(admin_add_code.this, Admin_Dashboard.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(admin_add_code.this, "Sorry! Unable To Add Code!", Toast.LENGTH_LONG).show();
                                    prog_bar.setVisibility(View.INVISIBLE);
                                }
                            }
                        });



//                a_code.add(code).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(admin_add_code.this, "Code Has Been Added Successfully!", Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(admin_add_code.this," Error " +e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });


            }
        });
    }
}