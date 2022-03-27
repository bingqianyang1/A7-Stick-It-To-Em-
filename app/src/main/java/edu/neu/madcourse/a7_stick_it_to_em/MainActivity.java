package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button send;
    Button sendHistory;
    Button receiveHistroy;
    private String user_name;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name = getIntent().getStringExtra("username");

        send = findViewById(R.id.send);
        sendHistory = findViewById(R.id.sendHistory);
        receiveHistroy = findViewById(R.id.receiveHistory);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this,SendStickerActivity.class);
                intent.putExtra("username", user_name);
                startActivity(intent);
            }
        });


        sendHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SendHistoryActivity.class);
                i.putExtra("username", user_name);
                startActivity(i);
            }
        });

        receiveHistroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowReceiveHistory.class);
                intent.putExtra("username", user_name);
                startActivity(intent);
            }
        });


        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Users");
        reference.child(user_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Toast.makeText(MainActivity.this, "CHANGED",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}