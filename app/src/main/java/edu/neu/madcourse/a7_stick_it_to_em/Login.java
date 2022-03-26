package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText input;
    Button btn;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input = findViewById(R.id.username);
        btn = findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, input.getText(),Toast.LENGTH_SHORT).show();
                User user = new User(input.getText().toString());
                reference.child(user.getUsername()).setValue(user);
                Intent intent = new Intent(Login.this,MainActivity.class);
                intent.putExtra("username", user.getUsername());
                startActivity(intent);
            }
        });
    }
}