package com.example.blind_assisting_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list_user extends AppCompatActivity {
    ListView lv;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        lv = (ListView) findViewById(R.id.lv);
        tv = (TextView) findViewById(R.id.tv);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot Snapshot : snapshot.getChildren()){
                    User userprofile = Snapshot.getValue(User.class);
                    long total_users = snapshot.getChildrenCount();
                    String name = userprofile.name;
                    String email = userprofile.email;
                    String code = userprofile.code;
                    String txt = userprofile.name;
//                    list.add(Snapshot.getValue().toString());
                    list.add(txt);
                    tv.setText("Total Users : "+total_users);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            int index = i;
                            Toast.makeText(list_user.this, " Name : "+name+ " Email : "+email+ " Code : "+code, Toast.LENGTH_LONG).show();
                        }
                    });
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}