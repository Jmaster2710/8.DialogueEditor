package com.example.joel.dialogueGame;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DialogueAdapter.DialogueClickListener {


    //Local variables


//Constants used when calling the update activity

    public static final String EXTRA_REMINDER = "Dialogue";

    public static final int REQUESTCODE = 1234;

    private int mModifyPosition;

    public static AppDatabase db;

    private DialogueAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Button mPlaytestButton;

    private MainViewModel mMainViewModel;

    private List<Dialogue> mDialogues;
    private EditText mNewDialogueText;

    private NameItem mName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get names from the API
        requestData();

        mRecyclerView = findViewById(R.id.recyclerView);
        mPlaytestButton = findViewById(R.id.playtest_button);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mMainViewModel = new MainViewModel(getApplicationContext());

        mMainViewModel.getDialogues().observe(this, new Observer<List<Dialogue>>() {

            @Override

            public void onChanged(@Nullable List<Dialogue> dialogues) {
                mDialogues = dialogues;
                updateUI();
            }

        });

        mNewDialogueText = findViewById(R.id.editText_main);
        mDialogues = new ArrayList<>();
        mPlaytestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startPlaytest();
            }
        });

        /*touch helper*/
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =

                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }


                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());

                        //mDialogues.remove(position);
                        mMainViewModel.delete(mDialogues.get(position));
                    }

                };


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                //Get the user text from the textfield

                String text = mNewDialogueText.getText().toString();

                //Check if some text has been added
                if (!(TextUtils.isEmpty(text))) {
                    //Add the text to the list (datamodel)
                    //mDialogues.add(newDialogue);
                    AddDialogue(text);


                } else {
                    //Show a message to the user if the textfield is empty
                    Snackbar.make(view, "Please enter some text in the textfield", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void updateUI() {

        if (mAdapter == null) {

            mAdapter = new DialogueAdapter(mDialogues, this);

            mRecyclerView.setAdapter(mAdapter);

        } else {

            mAdapter.swapList(mDialogues);

        }

    }

    private void AddDialogue(String text)
    {
        Dialogue newDialogue = new Dialogue(text, mName.getName());
        mMainViewModel.insert(newDialogue);

        //Initialize the EditText for the next item
        mNewDialogueText.setText("");

        requestData();
    }

    @Override
    public void reminderOnClick(int i) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        mModifyPosition = i;

        intent.putExtra(EXTRA_REMINDER, mDialogues.get(i));
        startActivityForResult(intent, REQUESTCODE);
    }


    public void startPlaytest() {

        ArrayList<String> textList = new ArrayList<String>();

        if (mDialogues.size() > 0) {

            for (Dialogue r : mDialogues) {
                textList.add(r.getDialogueText());
            }
            Intent intent = new Intent(MainActivity.this, PlaytestActivity.class);
            intent.putStringArrayListExtra("stringList", (ArrayList<String>) textList);
            startActivity(intent);
        }

    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == REQUESTCODE) {

            if (resultCode == RESULT_OK) {

                Dialogue updatedDialogue = data.getParcelableExtra(MainActivity.EXTRA_REMINDER);

                // New timestamp: timestamp of update

                mMainViewModel.update(updatedDialogue);

            }
        }
    }


    private void requestData()
    {
        Log.d("Debug", "API CALLED");
        NamesApiService service = NamesApiService.retrofit.create(NamesApiService.class);
        Call<NameItem> call = service.getNames();
        call.enqueue(new Callback<NameItem>() {
            @Override
            public void onResponse(Call<NameItem> call, Response<NameItem> response) {

                Log.d("Debug", "Response Succes =" + response.isSuccessful());

                Log.d("Debug", "Information got1: " + response.body());

                Log.d("Debug", "Information gotten: " + response.code());

                //mName.addAll(Arrays.asList(response.body().nameItems));
                mName = response.body();

                //Log.d("Debug", mName.get(1).getName());
            }

            @Override
            public void onFailure(Call<NameItem> call, Throwable t) {
                Log.d("error",t.toString());
            }

        });

    }


}