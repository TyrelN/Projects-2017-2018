package com.danilo.chiarlone.grpup21.projectstep4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class chatMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatmatch);

        final EditText editText = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotEmpty(editText.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Couldn't find a network connection. Please, try again later.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public static boolean isNotEmpty(String str) {

        if (str == null)
            return false;
        else if (str.toString().trim().length() == 0)
            return false;

        return true;
    }
    public void back(View view){
        finish();
    }
}
