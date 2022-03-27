package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button send;
    Button sendHistory;
    Button receiveHistory;
    private String userName;
    private FirebaseDatabase database;
    private String receiveNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = getIntent().getStringExtra("username");
        receiveNumber = getIntent().getStringExtra("number");
        send = findViewById(R.id.send);
        sendHistory = findViewById(R.id.sendHistory);
        receiveHistory = findViewById(R.id.receiveHistory);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent = new Intent(MainActivity.this,SendStickerActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });


        sendHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SendHistoryActivity.class);
                i.putExtra("username", userName);
                startActivity(i);
            }
        });

        receiveHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowReceiveHistory.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });


        // Set the references.
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Users");

        // Message Notification.
        reference.child(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // The number get from the login page.
                int originNumber = Integer.valueOf(receiveNumber);

                // The number after data change
                int currentNumber = Integer.valueOf(snapshot.child("received_Number").getValue().toString());

                // If the currentNumber is greater than origin one, which means there's a new sticker
                // received, then send the notification.
                if(currentNumber > originNumber) {
                    String newSticker = "";
                    for(DataSnapshot data: snapshot.child("received_history").getChildren()) {
                        newSticker = data.child("Sticker").getValue().toString();
                    }
                    //Send Notification
                    Notification.sendNotification(MainActivity.this, newSticker);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}