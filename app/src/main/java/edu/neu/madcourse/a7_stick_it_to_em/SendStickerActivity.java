package edu.neu.madcourse.a7_stick_it_to_em;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SendStickerActivity extends AppCompatActivity {
    private int selected_Sticker = 0;
    private Button send_button;
    private TextView receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sticker);
        send_button = (Button) findViewById(R.id.btn_sent);
        receiver = findViewById(R.id.receiver);

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
                else{

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
}