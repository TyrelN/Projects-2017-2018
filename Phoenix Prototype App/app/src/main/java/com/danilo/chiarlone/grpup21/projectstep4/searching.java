package com.danilo.chiarlone.grpup21.projectstep4;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import static android.widget.Toast.LENGTH_LONG;

public class searching extends Activity {
    Scanner in;
    StringBuffer full;
    ArrayList<String> textLines;
    //ArrayList<String[]> textSet;
    File file = new File("User.txt");
    EditText editName;
    String input;
    String found;
    String[] set;
    int lineNumber;
    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        Toast.makeText(this, "Loading Users", LENGTH_LONG).show();
        StringBuilder buff = new StringBuilder();
        buff.append("Jimmy Buffet" + "," + "Male" + "," + "\n");
        buff.append("Karol Masters" + "," + "Female" + "," +"\n");
        buff.append("Raphael Chivalry" + "," + "Male" + "," + "\n");
        buff.append("Hard Boyle" + "," + "Male" + ",");

        editName = findViewById(R.id.searchBar);
        input = editName.getText().toString();
        String done = buff.toString();
        try{
            FileOutputStream out = openFileOutput(file.toString(), Context.MODE_PRIVATE);
            try{
                out.write(done.getBytes()); //this may need to become an array of string values to match the places the messages will go - comma seperated so add a comma to every write
                out.close();

            }catch(IOException e){
                e.printStackTrace();
            }


        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    //https://netjs.blogspot.com/2016/02/how-to-read-file-from-last-line-in-java.html was referenced for the manipulation of file lines
    public void onClick(View view) {
        set = new String[2];
        editName = findViewById(R.id.searchBar);
        input = editName.getText().toString();
        try {

            textLines = new ArrayList<String>(5);
            FileInputStream fis = openFileInput(file.toString());
            InputStreamReader isr = new InputStreamReader(fis);
            in = new java.util.Scanner(isr).useDelimiter("\n");
            int i = 0;
            while (in.hasNext()) {
                String yes = in.next();
                textLines.add(yes);
                i++;
            }
           // try {
                for(int j = 0; j < textLines.size(); j++){
                    Toast.makeText(this, "testing " + textLines.get(j).substring(0,textLines.get(j).indexOf(",")) + " against " + input, LENGTH_LONG).show();
                    if(textLines.get(j).substring(0,textLines.get(j).indexOf(",")).equals(input)){
                        Toast.makeText(this, "Found 'em!", LENGTH_LONG).show();
                        set = textLines.get(j).split(",");
                        break;
                    }
                }
              if(set[0] != null) {
                    Toast.makeText(this, "Found User: " + set[0] + " with gender: " + set[1], LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(this, "Couldn't find user: " + input, LENGTH_LONG).show(); }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

}
public void back(View view){
        finish();
}
}
