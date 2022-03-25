package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText input;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input = findViewById(R.id.username);
        btn = findViewById(R.id.login);
    }
}