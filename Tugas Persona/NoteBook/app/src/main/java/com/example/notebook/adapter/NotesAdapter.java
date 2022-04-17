package com.example.notebook.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.R;
import com.example.notebook.activity.InsertNotesActivity;
import com.example.notebook.activity.MainActivity;
import com.example.notebook.activity.UpdateNotesActivity;
import com.example.notebook.model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesAdapter.notesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewholder holder, int position) {

        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }

        if ("1".equals(note.notesAlarm)) {
            holder.notesAlarm.setBackgroundResource(R.drawable.ic_baseline_alarm_on_24);
        } else if ("2".equals(note.notesAlarm)) {
            holder.notesAlarm.setBackgroundResource(0);
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);
        holder.notesReminder.setText(note.notesReminder);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
            intent.putExtra("id",note.id);
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subtitle",note.notesSubtitle);
            intent.putExtra("priority",note.notesPriority);
            intent.putExtra("alarm",note.notesAlarm);
            intent.putExtra("note",note.notes);
            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewholder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate, notesReminder;
        View notesPriority, notesAlarm;

        public notesViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
            notesReminder = itemView.findViewById(R.id.notesReminder);
            notesAlarm = itemView.findViewById(R.id.notesAlarm);
        }
    }
}
