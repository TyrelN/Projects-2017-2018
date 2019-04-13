package com.danilo.chiarlone.grpup21.projectstep4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void chat(View view){
        Intent intent = new Intent(this, matches.class);
        startActivity(intent);
    }
    public void search(View view){
        Intent intent = new Intent(this, searching.class);
        startActivity(intent);
    }
    public void match(View view){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
}
