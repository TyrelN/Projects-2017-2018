package com.danilo.chiarlone.grpup21.projectstep4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class matches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Button buttonChatMatch1 = findViewById(R.id.buttonChatMatch1);
        Button buttonChatMatch2 = findViewById(R.id.buttonChatMatch2);
        final Intent chatIntent = new Intent(getApplicationContext(), chatMatch.class); // creates intent to go to second activity

        buttonChatMatch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(chatIntent); // starting second activity from intent
            }
        });
        buttonChatMatch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(chatIntent); // starting second activity from intent
            }
        });
    }
    public void back(View view){
        finish();
    }

}
