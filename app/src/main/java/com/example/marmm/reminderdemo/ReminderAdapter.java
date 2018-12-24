package com.example.marmm.reminderdemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {


    private List<Reminder> mReminders;


    final private ReminderClickListener mReminderClickListener;

    public interface ReminderClickListener{

        void reminderOnClick (int i);

    }

    public ReminderAdapter(List<Reminder> mReminder, ReminderClickListener mReminderClickListener) {
        this.mReminders = mReminder;
        this.mReminderClickListener = mReminderClickListener;
    }

    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.textbox_cell, null);

// Return a new holder instance
        ReminderAdapter.ViewHolder viewHolder = new ReminderAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ViewHolder viewHolder, int i) {

        Reminder reminder =  mReminders.get(i);

        //Sets text of the dialogue
        viewHolder.textView.setText(reminder.getReminderText());
        viewHolder.nameView.setText(reminder.getReminderName());
    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textView;
        public TextView nameView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.dialogueText);
            nameView = itemView.findViewById(R.id.dialogueName);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mReminderClickListener.reminderOnClick(clickedPosition);
        }
    }


    public void swapList (List<Reminder> newList) {
        mReminders = newList;

        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();

        }

    }

}
