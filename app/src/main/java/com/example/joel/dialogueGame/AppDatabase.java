package com.example.joel.dialogueGame;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Dialogue.class}, version = 4)

public abstract class AppDatabase extends RoomDatabase {

    public abstract DialogueDao dialogueDao();
    private final static String NAME_DATABASE = "dialogue_db";


    //Static instance

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {


        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return sInstance;

    }

}
