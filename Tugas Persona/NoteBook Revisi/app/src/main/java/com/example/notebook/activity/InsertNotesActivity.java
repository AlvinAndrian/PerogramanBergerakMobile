package com.example.notebook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notebook.R;
import com.example.notebook.databinding.ActivityInsertNotesBinding;
import com.example.notebook.model.Notes;
import com.example.notebook.viewmodel.NotesViewModel;

import java.sql.Time;
import java.text.BreakIterator;
import java.util.Calendar;
import java.util.Date;
import android.text.format.DateFormat;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes, tanggal, waktu;
    NotesViewModel notesViewModel;
    String priority = "1";
    TextView mTanggal;
    TextView mWaktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mTanggal = findViewById(R.id.notesTanggal);
        mWaktu = findViewById(R.id.notesWaktu);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        InsertNotesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date  = day+"/"+month+"/"+year;
                        mTanggal.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        mWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(InsertNotesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        mWaktu.setText(h + ":" + m);
                    }
                },hour,minute, true);
                timePickerDialog.show();
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

        binding.doneNotesBtn.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            subtitle = binding.notesSubtitle.getText().toString();
            notes = binding.notesData.getText().toString();
            tanggal = binding.notesTanggal.getText().toString();
            waktu = binding.notesWaktu.getText().toString();

            CreateNotes(title, subtitle, notes, tanggal, waktu);

        });
    }

    private void CreateNotes(String title, String subtitle, String notes, String tanggal, String waktu) {

        Date date  = new Date();
        CharSequence sequence = DateFormat.format("dd/MM/yyyy hh:mm:ss", date).toString();

        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notesPriority = priority;
        notes1.notesDate = sequence.toString();
        notes1.notesTanggal = tanggal;
        notes1.notesWaktu = waktu;
        notes1.notes = notes;

        notesViewModel.insertNote(notes1);
        Toast.makeText(this,"Created Success", Toast.LENGTH_SHORT).show();
        finish();
    }
}