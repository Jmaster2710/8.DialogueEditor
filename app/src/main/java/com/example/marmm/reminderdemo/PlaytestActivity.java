package com.example.marmm.reminderdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaytestActivity extends AppCompatActivity {

    public Button mButtonBack;
    public TextView mDialogue;
    public String mDrawString;
    public ArrayList<String> mStrings = new ArrayList<String>();

    public int mStringIndex;
    public int mCharIndex;

    public int mTextDelay = 100;

    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerHandler.postDelayed(this, mTextDelay);

            //As long as the sentenced is not finished
            if (mDrawString.length() < mStrings.get(mStringIndex).length()) {
                mDrawString += mStrings.get(mStringIndex).charAt(mCharIndex);
                mCharIndex ++;
            }
            mDialogue.setText(String.format("%d:%02d", minutes, seconds) + mDrawString);

        }
    };

    public void nextText()
    {
        //Has the dialogue finished showing all text
        if (mDrawString.length() == mStrings.get(mStringIndex).length())
        {
            //Go to next one.
            if (mStringIndex < mStrings.size() - 1)
            {
                mStringIndex++;
                mCharIndex = 0;
                mDrawString = "";
            } else
            {
                endTest();
            }

        } else
        {
            mDrawString = mStrings.get(mStringIndex);
        }


    }

    public void endTest()
    {
        Intent intent = new Intent(PlaytestActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playtest);

        //Get all the dialogue that needs to be played
        mStrings = getIntent().getStringArrayListExtra("stringList");

        mDialogue = findViewById(R.id.dialogueText);

        //For showing the text one by one
        mCharIndex = 0;
        mStringIndex = 0;

        mDrawString = "";
       // mDialogue.setText(mStrings.get(0));

        mDialogue.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                nextText();
            }
        });

        mButtonBack = findViewById(R.id.button_back);
        mButtonBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                endTest();
            }
        });

        //Timer
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

    }
}
