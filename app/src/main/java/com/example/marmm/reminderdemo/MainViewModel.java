package com.example.marmm.reminderdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {
    private ReminderRepository mRepository;

    private LiveData<List<Reminder>> mReminders;


    public MainViewModel(Context context) {

        mRepository = new ReminderRepository(context);

        mReminders = mRepository.getAllReminders();

    }


    public LiveData<List<Reminder>> getReminders() {
        return mReminders;
    }


    public void insert(Reminder reminder) {
        mRepository.insert(reminder);
    }


    public void update(Reminder reminder) {
        mRepository.update(reminder);
    }


    public void delete(Reminder reminder) {

        mRepository.delete(reminder);
    }
}
