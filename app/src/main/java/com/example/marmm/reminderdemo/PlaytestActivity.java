package com.example.marmm.reminderdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PlaytestActivity extends AppCompatActivity {

    public Button mButtonBack;
    public ArrayList<String> mStrings = new ArrayList<String>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playtest);

        //Get all the dialogue that needs to be played
        mStrings = getIntent().getStringArrayListExtra("stringList");

        mButtonBack = findViewById(R.id.button_back);
        mButtonBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(PlaytestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
