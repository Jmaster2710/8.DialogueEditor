package com.example.joel.dialogueGame;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ReminderRepository {

    private AppDatabase mAppDatabase;

    private ReminderDao mReminderDao;

    private LiveData<List<Reminder>> mReminders;

    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public ReminderRepository (Context context) {

        mAppDatabase = AppDatabase.getInstance(context);

        mReminderDao = mAppDatabase.reminderDao();

        mReminders = mReminderDao.getAllReminders();

    }


    public LiveData<List<Reminder>> getAllReminders() {

        return mReminders;

    }


    public void insert(final Reminder reminder) {

        mExecutor.execute(new Runnable() {

            @Override

            public void run() {

                mReminderDao.insertReminders(reminder);

            }

        });

    }


    public void update(final Reminder reminder) {

        mExecutor.execute(new Runnable() {

            @Override

            public void run() {

                mReminderDao.updateReminders(reminder);

            }

        });

    }


    public void delete(final Reminder reminder) {

        mExecutor.execute(new Runnable() {

            @Override

            public void run() {

                mReminderDao.deleteReminders(reminder);

            }

        });

    }
}
