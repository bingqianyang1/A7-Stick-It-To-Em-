package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SendHistoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_history);

        TextView sendingCount = findViewById(R.id.sendingCount);

        String user_name = getIntent().getStringExtra("username");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(user_name).child("sent");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder res = new StringBuilder();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    res.append(dataSnapshot.getValue().toString()).append("  ");
                }
                sendingCount.setText(res.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }






}