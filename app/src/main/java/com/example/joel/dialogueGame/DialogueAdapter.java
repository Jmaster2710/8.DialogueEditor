package com.example.joel.dialogueGame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DialogueAdapter extends RecyclerView.Adapter<DialogueAdapter.ViewHolder> {


    private List<Dialogue> mDialogues;


    final private DialogueClickListener mDialogueClickListener;

    public interface DialogueClickListener {

        void reminderOnClick (int i);

    }

    public DialogueAdapter(List<Dialogue> mDialogue, DialogueClickListener mDialogueClickListener) {
        this.mDialogues = mDialogue;
        this.mDialogueClickListener = mDialogueClickListener;
    }

    @NonNull
    @Override
    public DialogueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.textbox_cell, null);

// Return a new holder instance
        DialogueAdapter.ViewHolder viewHolder = new DialogueAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DialogueAdapter.ViewHolder viewHolder, int i) {

        Dialogue dialogue =  mDialogues.get(i);

        //Sets text of the dialogue
        viewHolder.textView.setText(dialogue.getDialogueText());
        viewHolder.nameView.setText(dialogue.getDialogueName());
    }

    @Override
    public int getItemCount() {
        return mDialogues.size();
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
            mDialogueClickListener.reminderOnClick(clickedPosition);
        }
    }


    public void swapList (List<Dialogue> newList) {
        mDialogues = newList;

        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();

        }

    }

}
