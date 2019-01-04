package com.example.joel.dialogueGame;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DialogueDao {

    @Query("SELECT * FROM dialogue")
    public LiveData<List<Dialogue>> getAllDialogues();


    @Insert
    public void insertDialogues(Dialogue reminders);


    @Delete
    public void deleteDialogues(Dialogue reminders);


    @Update
    public void updateDialogues(Dialogue reminders);

}