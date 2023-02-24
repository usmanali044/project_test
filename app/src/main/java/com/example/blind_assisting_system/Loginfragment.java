package com.example.blind_assisting_system;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.blind_assisting_system.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import java.util.concurrent.Executor;
public class Loginfragment extends Fragment {
//    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    TextView fpass;
    Button lbtn;
    EditText ed1,ed2;
//    ProgressBar prog_bar;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loginfragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        ed1 = (EditText) view.findViewById(R.id.ed1);
        ed2 = (EditText) view.findViewById(R.id.ed2);
        fpass = (TextView) view.findViewById(R.id.fpass);
        lbtn = (Button) view.findViewById(R.id.lbtn);
//        prog_bar = (ProgressBar) view.findViewById(R.id.progbar);

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), resetpass.class));
            }
        });


        return view;

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

//        prog_bar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            prog_bar.setVisibility(View.INVISIBLE);

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            Email Verification
                            if(user.isEmailVerified()){
                                Toast.makeText(getActivity(), "LogedIn Successful", Toast.LENGTH_LONG).show();

                                if("uaciit@gmail.com".equals(email)){
                                    Intent intent = new Intent(getActivity(), Admin_Dashboard.class);
                                    startActivity(intent);
                                }else{
                                    Intent intent = new Intent(getActivity(), Dashboard.class);
                                    startActivity(intent);
                                }

                            }else{
                                user.sendEmailVerification();
                                Toast.makeText(getActivity(), "Verification mail has been sent, kindly verify account!", Toast.LENGTH_LONG).show();
                            }

                        }else{
//                            prog_bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), "Invalid Email Or Password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}