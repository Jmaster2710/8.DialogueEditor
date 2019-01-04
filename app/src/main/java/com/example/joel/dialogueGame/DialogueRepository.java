package com.example.joel.dialogueGame;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DialogueRepository {

    private AppDatabase mAppDatabase;

    private DialogueDao mDialogueDao;

    private LiveData<List<Dialogue>> mDialogues;

    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public DialogueRepository(Context context) {

        mAppDatabase = AppDatabase.getInstance(context);

        mDialogueDao = mAppDatabase.dialogueDao();

        mDialogues = mDialogueDao.getAllDialogues();

    }


    public LiveData<List<Dialogue>> getAllDialogues() {

        return mDialogues;

    }


    public void insert(final Dialogue dialogue) {

        mExecutor.execute(new Runnable() {

            @Override

            public void run() {

                mDialogueDao.insertDialogues(dialogue);

            }

        });

    }


    public void update(final Dialogue dialogue) {

        mExecutor.execute(new Runnable() {

            @Override

            public void run() {

                mDialogueDao.updateDialogues(dialogue);
            }

        });

    }


    public void delete(final Dialogue dialogue) {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDialogueDao.deleteDialogues(dialogue);
            }
        });

    }
}
