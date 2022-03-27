package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;



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
    private String receiveNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name = getIntent().getStringExtra("username");
        receiveNumber = getIntent().getStringExtra("number");
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

        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("Users");
        // Message Notification
        reference.child(user_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int originNumber = Integer.valueOf(receiveNumber);
                int currentNumber = Integer.valueOf(snapshot.child("received_Number").getValue().toString());
                if(currentNumber > originNumber) {
                    String newSticker = "";
                    for(DataSnapshot data: snapshot.child("received_history").getChildren()) {
                        newSticker = data.child("Sticker").getValue().toString();
                    }
                    Toast.makeText(MainActivity.this,  "HEY " +
                            newSticker, Toast.LENGTH_LONG).show();
                    Notification.sendNotification(MainActivity.this, newSticker);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}