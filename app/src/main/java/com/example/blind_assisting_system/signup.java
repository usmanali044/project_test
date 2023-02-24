package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class signup extends Fragment {
    private FirebaseAuth mAuth;
    TextView login;
    EditText name, email,pass,code;
    Button register_btn;
    ProgressBar progress_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        inflater.inflate(R.layout.fragment_signup, container, false);


        mAuth = FirebaseAuth.getInstance();
        name =  (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email);
        pass = (EditText) view.findViewById(R.id.pass);
        code = (EditText) view.findViewById(R.id.code);
//        progress_bar = (ProgressBar) view.findViewById(R.id.progbar);
        register_btn = (Button) view.findViewById(R.id.Rbtn);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "User Has Been Registered Successfully", Toast.LENGTH_LONG).show();
                registerUser();
            }
        });
        return view;
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


//        progress_bar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(u_email, u_pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

//                            Toast.makeText(getActivity(), "User Has Been Registered Successfully", Toast.LENGTH_LONG).show();
//                            progress_bar.setVisibility(View.INVISIBLE);

                            User user = new User(u_name,u_email,u_code, u_pass);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getActivity(), "User Has Been Registered Successfully", Toast.LENGTH_LONG).show();
//                                                progress_bar.setVisibility(View.INVISIBLE);
                                                Intent intent = new Intent(getActivity(), MainActivity3.class);
                                                startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(getActivity(), "Sorry! Unable To Register!", Toast.LENGTH_LONG).show();
//                                                progress_bar.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_LONG).show();
//                            progress_bar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}