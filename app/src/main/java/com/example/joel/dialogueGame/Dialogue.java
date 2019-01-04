package com.example.joel.dialogueGame;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "dialogue")
public class Dialogue implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "dialoguetext")
    private String mDialogueText;

    @ColumnInfo(name = "dialoguename")
    private String mDialogueName;


    public Dialogue(String mDialogueText, String mDialogueName) {
        this.mDialogueText = mDialogueText;
        this.mDialogueName = mDialogueName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return mDialogueText;
    }

    public String getDialogueText() {
        return mDialogueText;
    }

    public void setDialogueText(String mDialogueText) {
        this.mDialogueText = mDialogueText;
    }


    public String getDialogueName() {
        return mDialogueName;
    }

    public void setDialogueName(String mDialogueName) {
        this.mDialogueName = mDialogueName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDialogueName);
        dest.writeString(this.mDialogueText);
        dest.writeValue(this.id);
    }

    protected Dialogue(Parcel in) {
        this.mDialogueName = in.readString();
        this.mDialogueText = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Dialogue> CREATOR = new Creator<Dialogue>() {
        @Override
        public Dialogue createFromParcel(Parcel source) {
            return new Dialogue(source);
        }

        @Override
        public Dialogue[] newArray(int size) {
            return new Dialogue[size];
        }
    };
}
