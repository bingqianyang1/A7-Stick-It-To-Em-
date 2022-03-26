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
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SendStickerActivity extends AppCompatActivity {
    private int selected_Sticker = 0;
    private String selected_Sticker_String = "";
    private Button send_button;
    private TextView receiver;
    private FirebaseDatabase database;
    private User user;
    private String user_name;
    private String receiver_name;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<String> active_user_list = new ArrayList<>();
    private String s;
    private String date_String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sticker);
        send_button = (Button) findViewById(R.id.btn_sent);
        receiver = findViewById(R.id.receiver);
        user_name = getIntent().getStringExtra("username");
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        if (user_name == null){
            alertDialog.setMessage("Please select an image").show();
        }

        database = FirebaseDatabase.getInstance();
        database.getReference().child("Users").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                user = (User) snapshot.getValue(User.class);
                assert user != null;
                if (!user.getUsername().equals(user_name)) {
                    users.add(user);
                    active_user_list.add(user.getUsername());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                user = (User) snapshot.getValue(User.class);
                if (Objects.requireNonNull(snapshot.getKey()).equalsIgnoreCase(user_name)) {
                    TextView textView = findViewById(R.id.selectEmoji);

                    //Display the User name
                    textView.setText(String.format("User name: %s", user.getUsername()));
                }
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



        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                /** Search the receiver from the database */
                /** To be add Here*/
                if (selected_Sticker == 0 || selected_Sticker_String == "") {
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
                            for (User user : users) {
                                if (user.getUsername().equals(receiver.getText().toString())) {
                                    if (user.received == null){
                                        user.setReceived();
                                    }
                                    receiver_name = user.getUsername();
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                                    Date date = new Date(System.currentTimeMillis());
                                    date_String = formatter.format(date).toString();

                                    updateSender(database.getReference());
                                    updateReceiver(database.getReference());



                                    /** send message to receiver add here */
//                                            sendMessageToSpecUser(user.CLIENT_REGISTRATION_TOKEN);
//                                            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();

                                    receiver.setText("");
                                }
                            }
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
                selected_Sticker_String = "Sticker1";
                break;
            case R.id.sticker2:
                selected_Sticker = R.drawable.sticker2;
                selected_Sticker_String = "Sticker2";
                break;
            case R.id.sticker3:
                selected_Sticker = R.drawable.sticker3;
                selected_Sticker_String = "Sticker3";
                break;
            case R.id.sticker4:
                selected_Sticker = R.drawable.sticker4;
                selected_Sticker_String = "Sticker4";
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

    private void updateSender(DatabaseReference database) {
        database.child("Users").child(user_name).runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                User user = mutableData.getValue(User.class);
                if (user == null) {
                    return Transaction.success(mutableData);
                }

                switch (selected_Sticker_String) {
                    case "Sticker1":
                        user.sent.put("Sticker1", user.sent.get("Sticker1") + 1);
                        break;
                    case "Sticker2":
                        user.sent.put("Sticker2", user.sent.get("Sticker2") + 1);
                        break;
                    case "Sticker3":
                        user.sent.put("Sticker3", user.sent.get("Sticker3") + 1);
                        break;
                    case "Sticker4":
                        user.sent.put("Sticker4", user.sent.get("Sticker4") + 1);
                        break;
                }

//                user.sentCount ++;
                mutableData.setValue(user);

                return Transaction.success(mutableData);
            }



            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                //N/A
            }


        });

//        database.child("Users").child(user_name).child("sent_history").runTransaction(new Transaction.Handler() {
//            @NonNull
//            @Override
//            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
////                User user = mutableData.getValue(User.class);
////                if (user == null) {
////                    return Transaction.success(mutableData);
////                }
//                user.sent_history.put(receiver_name, selected_Sticker_String);
//                mutableData.setValue(user.sent_history);
//
//                return Transaction.success(mutableData);
//            }
//
//            @Override
//            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
//
//            }
//        });


    }

    private void updateReceiver(DatabaseReference database) {
        database.child("Users").child(receiver_name).runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                User user = mutableData.getValue(User.class);
                if (user == null) {
                    return Transaction.success(mutableData);
                }

                user.received_Number++;
                switch (selected_Sticker_String) {
                    case "Sticker1":
                        user.received.put("Sticker1", user.received.get("Sticker1") + 1);
                        break;
                    case "Sticker2":
                        user.received.put("Sticker2", user.received.get("Sticker2") + 1);
                        break;
                    case "Sticker3":
                        user.received.put("Sticker3", user.received.get("Sticker3") + 1);
                        break;
                    case "Sticker4":
                        user.received.put("Sticker4", user.received.get("Sticker4") + 1);
                        break;
                }

//                user.sentCount ++;
                mutableData.setValue(user);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                //N/A
            }


        });

        database.child("Users").child(receiver_name).child("received_history").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {

                    Map<String, Object> aHistory = new HashMap<>();
                    aHistory.put("Sender", user_name);
                    aHistory.put("Sticker", selected_Sticker_String);
                    aHistory.put("Time", date_String);

                    Map<String, Object> received_history = new HashMap<>();
                    received_history.put("History1", aHistory);

                    Map<String, Object> updated = new HashMap<>();
                    updated.put("received_history", received_history);
                    database.child("Users").child(receiver_name).updateChildren(updated);
                }else {

//                user.sent_history.put("History" + user.sent_history.size(),receiver_name + ": " + selected_Sticker_String);

                    Map<String, Object> aHistory = new HashMap<>();
                    aHistory.put("Sender", user_name);
                    aHistory.put("Sticker", selected_Sticker_String);
                    aHistory.put("Time", date_String);

                    Map<String, Object> updated = new HashMap<>();
                    updated.put("History" + user.received_Number, aHistory);
                    database.child("Users").child(receiver_name).child("received_history").updateChildren(updated);
                }
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}