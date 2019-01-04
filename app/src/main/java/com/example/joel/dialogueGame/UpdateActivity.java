package com.example.joel.dialogueGame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {



    private EditText mDialogueView;
    private TextView mNameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Init local variables

        mDialogueView = findViewById(R.id.editText_update);
        mNameView = findViewById(R.id.view_name);


//Obtain the parameters provided by MainActivity

        final Dialogue dialogueUpdate = getIntent().getParcelableExtra(MainActivity.EXTRA_REMINDER);

        mDialogueView.setText(dialogueUpdate.getDialogueText());
        mNameView.setText(dialogueUpdate.getDialogueName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = mDialogueView.getText().toString();



                if (!TextUtils.isEmpty(text)) {
                    dialogueUpdate.setDialogueText(text);

                    //Prepare the return parameter and return
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.EXTRA_REMINDER, dialogueUpdate);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();

                } else {
                    Snackbar.make(view, R.string.enter_text, Snackbar.LENGTH_LONG);
                }


            }
        });
    }

}
