package com.danilo.chiarlone.grpup21.projectstep4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile extends Activity {
    String[] details;
    TextView text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        text1 = findViewById(R.id.name);
        text2 = findViewById(R.id.age);
        text3 = findViewById(R.id.gender);
        text4 = findViewById(R.id.likes);

        Bundle bundle = getIntent().getExtras();
        details = new String[4];
        if (bundle.getInt("count") == 0) {
            details[0] = "Gilbert";
            details[1] = "83";
            details[2] = "Male";
            details[3] = "Photography, Quantum Physics";
        }else if (bundle.getInt("count") == 1) {
            details[0] = "Cynthia";
            details[1] = "96";
            details[2] = "Female";
            details[3] = "This app, Younger Men";
        }else{
            details[0] = "N/A";
            details[1] = "N/A";
            details[2] = "N/A";
            details[3] = "N/A";
        }
        text1.setText(details[0]);
        text2.setText(details[1]);
        text3.setText(details[2]);
        text4.setText(details[3]);
    }
    public void back(View view){
        finish();
    }
}
