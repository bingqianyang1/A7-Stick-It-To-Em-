package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText input;
    // LoginButton
    Button loginButton;
    private String username;
    // How many received stickers this user has, default 0.
    private String number = "0";
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input = findViewById(R.id.username);
        loginButton = findViewById(R.id.login);

        // Disable the loginButton before clicked CHECK
        loginButton.setEnabled(false);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                String loginUsername = input.getText().toString();

                // check if the login UserName is the same as the checked one.
                if(!loginUsername.equals(username)) {
                    Toast.makeText(Login.this, "Please check your username first.",Toast.LENGTH_SHORT).show();
                    return;
                }

                // Welcome message.
                Toast.makeText(Login.this, "Welcome! " + input.getText(),Toast.LENGTH_SHORT).show();

                // Set the new user to firebase
                reference.child(username).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()) {
                            User user = new User(username);
                            reference.child(user.getUsername()).setValue(user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                //Pass the values to the MainActivity
                intent.putExtra("username", username);
                intent.putExtra("number", number);

                //Start new Activity
                startActivity(intent);
            }
        });
    }

    // Function when clicked the CHECK button
    public void onClickCheck(View view) {
        EditText text = findViewById(R.id.username);
        String input = text.getText().toString();
        reference.child(input).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username = input;
                if(snapshot.exists()) {
                    String currNumber = snapshot.child("received_Number").getValue().toString();
                    number = currNumber;
                    Toast.makeText(Login.this, "You have data in our app, please log in." ,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "You are a new User, please log in." ,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Enable the login button
        loginButton.setEnabled(true);
    }
}