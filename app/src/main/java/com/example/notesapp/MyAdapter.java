package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NoteViewHolder> {

    private Context mContext;
    private List< NoteData > mNoteList;
    private static ClickListener clickListener;

    MyAdapter(Context mContext, List< NoteData > mFlowerList) {
        this.mContext = mContext;
        this.mNoteList = mFlowerList;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);
        return new NoteViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position){

        holder.title.setText(mNoteList.get(position).getTitle());
        holder.content.setText(mNoteList.get(position).getContent());
        holder.last_edited.setText(mNoteList.get(position).getLastEdited());
        holder.card.setBackgroundColor(ContextCompat.getColor(mContext,mNoteList.get(position).getColor()));

    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title,content,last_edited;
        ImageButton edit,delete;
        RelativeLayout card;

        NoteViewHolder(View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.card);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.note_content);
            last_edited = itemView.findViewById(R.id.date);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }

        @Override
        public void onClick(View v) {

        }
    }
    public interface ClickListener {
        void onItemClick(int position, View v);
    }

}