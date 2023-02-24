package com.example.blind_assisting_system;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_code {

    private DatabaseReference databaseReference;

    public add_code(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Code.class.getSimpleName());
    }

    public Task<Void> add(Code code){

        return databaseReference.push().setValue(code);
    }
}
