package com.example.joel.dialogueGame;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {
    private DialogueRepository mRepository;

    private LiveData<List<Dialogue>> mDialogues;


    public MainViewModel(Context context) {

        mRepository = new DialogueRepository(context);
        mDialogues = mRepository.getAllDialogues();

    }


    public LiveData<List<Dialogue>> getDialogues() {
        return mDialogues;
    }

    public void insert(Dialogue dialogue) {
        mRepository.insert(dialogue);
    }

    public void update(Dialogue dialogue) {
        mRepository.update(dialogue);
    }

    public void delete(Dialogue dialogue) { mRepository.delete(dialogue); }
}
