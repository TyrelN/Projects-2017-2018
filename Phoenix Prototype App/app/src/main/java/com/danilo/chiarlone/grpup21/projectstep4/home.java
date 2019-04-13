package com.danilo.chiarlone.grpup21.projectstep4;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

//https://stackoverflow.com/questions/27086973/change-two-images-on-button-click-android was referenced
public class home extends Activity {
    TextView text;
    ImageView image;
    String[] names;
    int count; //a counter just to iterate through our current list of users to show a quick proof of concept
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        count = 0;
        text = (TextView) findViewById(R.id.name);
        names = new String[3];
        names[0] = "Gilbert";
        names[1] = "Cynthia";
        names[2] = "That's the last of your area's users";

        image = (ImageView) findViewById(R.id.picture);
        image.setImageResource(R.drawable.boy);
        text.setText(names[0]);

    }
    public void matchClick(View view){
       // Toast.makeText(this, " " + "Gilbert", LENGTH_LONG).show();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    public void friendClick(View view){
        if(count < 2 ){

        Toast.makeText(this, "You have chosen to friend " + names[count], LENGTH_LONG).show();
            count++;
        changePic();}else{
            Toast.makeText(this, "Woah there, friend", LENGTH_LONG).show();
        }

    }
    public void romanceClick(View view){
        if(count <2){

        Toast.makeText(this, "You have chosen to romance " + names[count], LENGTH_LONG).show();
            count++;
        changePic();}else{
            Toast.makeText(this, "Hold on there stud", LENGTH_LONG).show();

        }

    }
    public void passClick(View view){
        if(count < 2){

        Toast.makeText(this, "Moving on to next candidate", LENGTH_LONG).show();
            count++;
        changePic();}else{
            Toast.makeText(this, "Didn't you read the text?", LENGTH_LONG).show();
        }

    }
    public void onClick(View view){
        Intent intent = new Intent(this, searching.class);
        startActivity(intent);
    }
    public void changePic(){
        if(count < 3){

        switch(count) {
            case 0:
                image.setImageResource(R.drawable.boy);
                text.setText(names[count]);

                break;
            case 1:
                image.setImageResource(R.drawable.girl);
                text.setText(names[count]);

                break;
            case 2:
                image.setImageResource(R.drawable.none);
                text.setText(names[count]);

                break;
        }}


    }
    public void previous(View view){
        Toast.makeText(this, "changing to last user", LENGTH_LONG).show();
        if(count > 0){
            count--;
        }

        changePic();
    }
    public void viewProfile(View view){
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("count", count);
        startActivity(intent);
    }
}
