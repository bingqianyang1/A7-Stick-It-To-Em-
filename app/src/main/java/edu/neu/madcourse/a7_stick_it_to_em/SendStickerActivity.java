package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

public class SendStickerActivity extends AppCompatActivity {
    private int selected_Sticker = 0;
    private Button send_button;
    private TextView receiver;
    private FirebaseDatabase database;
    private String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sticker);
        send_button = (Button) findViewById(R.id.btn_sent);
        receiver = findViewById(R.id.receiver);

        database = FirebaseDatabase.getInstance();
        database.getReference().child("users").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                user_name = snapshot.getValue().toString();
//
//                assert user != null;
//                if (!user.username.equals(username)) {
//                    users.add(user);
//                    active_user_list.add(user.username);
////                    adapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                user = snapshot.getValue(User.class);
//                if (Objects.requireNonNull(snapshot.getKey()).equalsIgnoreCase(username)) {
//                    TextView textView = findViewById(R.id.selectEmoji);
//
//                    //Display how many stickers a user has sent
//                    textView.setText(
//                            String.format("%s" + " has sent %s stickers!", user.username, user.sentCount)
//                    );
//                }
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

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                /** Search the receiver from the database */
                /** To be add Here*/
                if (selected_Sticker == 0) {
                    alertDialog.setMessage("Please select an image").show();
                }
                /**
//                else if ("Can't find the User in database") {
//                    new AlertDialog.Builder(this).setMessage("Please select an User").show();
                 */
                else {
                    int SDK_INT = android.os.Build.VERSION.SDK_INT;
                    if (SDK_INT > 8) {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        if (isNetworkOnline()) {
                            //update the send count in database
//                            updateCount(database);
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    for (User user : users) {
//                                        if (user.username.equals(selectedUserName)) {
//                                            sendHistory.put(user.username, selectedSticker);
//
//                                            sendMessageToSpecUser(user.CLIENT_REGISTRATION_TOKEN);
//                                            selectedUserName = "";
//                                        }
//                                    }
//                                }
//                            }).start();
                        } else {
                            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    public void clickImg(View view) {
        switch (view.getId()) {
            case R.id.sticker1:
                selected_Sticker = R.drawable.sticker1;
                break;
            case R.id.sticker2:
                selected_Sticker = R.drawable.sticker2;
                break;
            case R.id.sticker3:
                selected_Sticker = R.drawable.sticker3;
                break;
            case R.id.sticker4:
                selected_Sticker = R.drawable.sticker4;
                break;
        }
    }


    public static boolean isNetworkOnline() {
        boolean isOnline = false;
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("8.8.8.8", 53), 3000);
            // socket.connect(new InetSocketAddress("114.114.114.114", 53), 3000);
            isOnline = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isOnline;
    }

}