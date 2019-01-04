package com.example.joel.dialogueGame;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity (tableName = "reminder")
public class Reminder implements Parcelable {


    @PrimaryKey (autoGenerate = true)
    private Long id;

    @ColumnInfo (name = "remindertext")
    private String mReminderText;

    @ColumnInfo (name = "remindername")
    private String mReminderName;


    public Reminder(String mReminderText, String mReminderName) {
        this.mReminderText = mReminderText;
        this.mReminderName = mReminderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return mReminderText;
    }

    public String getReminderText() {
        return mReminderText;
    }
    public void setReminderText(String mReminderText) {
        this.mReminderText = mReminderText;
    }


    public String getReminderName() {
        return mReminderName;
    }
    public void setReminderName(String mReminderName) {
        this.mReminderName = mReminderName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mReminderText);
        dest.writeValue(this.id);
    }

    protected Reminder(Parcel in) {
        this.mReminderText = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Reminder> CREATOR = new Creator<Reminder>() {
        @Override
        public Reminder createFromParcel(Parcel source) {
            return new Reminder(source);
        }

        @Override
        public Reminder[] newArray(int size) {
            return new Reminder[size];
        }
    };
}
