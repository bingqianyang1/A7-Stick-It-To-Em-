package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button send;
    Button sendHistory;
    Button receiveHistroy;
    private String user_name;

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
    }
}