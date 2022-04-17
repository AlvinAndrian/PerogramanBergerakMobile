package com.example.notebook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notebook.R;
import com.example.notebook.databinding.ActivityInsertNotesBinding;
import com.example.notebook.model.Notes;
import com.example.notebook.viewmodel.NotesViewModel;

import java.util.Calendar;
import java.util.Date;
import android.text.format.DateFormat;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes, reminder;
    NotesViewModel notesViewModel;
    TextView notesReminder;
    String priority = "1";
    String alarm = "2";
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch notesAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesReminder = findViewById(R.id.notesReminder);
        notesAlarm = findViewById(R.id.notesAlarm);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        notesReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InsertNotesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date  = day+"/"+month+"/"+year;
                        notesReminder.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.ic_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";
        });

        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_done_24);
            binding.redPriority.setImageResource(0);
            priority = "2";
        });

        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_done_24);
            priority = "3";
        });

        notesAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alarm ="1";
                } else {
                    alarm ="2";
                }
            }});

        binding.doneNotesBtn.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();
            reminder = binding.notesReminder.getText().toString();

            CreateNotes(title, subtitle, notes, reminder);

        });
    }

    private void CreateNotes(String title, String subtitle, String notes, String reminder) {

        Date date  = new Date();
        CharSequence sequence = DateFormat.format("dd/MM/yyyy hh:mm:ss", date).toString();

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notesPriority = priority;
        notes1.notesDate = sequence.toString();
        notes1.notesReminder = reminder;
        notes1.notesAlarm = alarm;
        notes1.notes = notes;

        notesViewModel.insertNote(notes1);
        Toast.makeText(this,"Created Success", Toast.LENGTH_SHORT).show();
        finish();
    }
}