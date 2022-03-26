package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button send;
    Button sendHistory;
    Button receiveHistroy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.send);
        sendHistory = findViewById(R.id.sendHistory);
        receiveHistroy = findViewById(R.id.receiveHistory);
    }
}