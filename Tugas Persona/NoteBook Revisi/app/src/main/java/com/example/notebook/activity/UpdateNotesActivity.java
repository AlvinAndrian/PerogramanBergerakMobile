package com.example.notebook.activity;

import androidx.annotation.NonNull;
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
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.notebook.R;
import com.example.notebook.databinding.ActivityUpdateNotesBinding;
import com.example.notebook.model.Notes;
import com.example.notebook.viewmodel.NotesViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;
import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    String priority;
    String stitle, ssubtitle, snotes, spriority, stanggal, swaktu;
    NotesViewModel notesViewModel;
    int sid;
    TextView sTanggal, sWaktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sWaktu = findViewById(R.id.upWaktu);
        sTanggal = findViewById(R.id.upTanggal);

        sid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        stanggal = getIntent().getStringExtra("tanggal");
        swaktu = getIntent().getStringExtra("waktu");
        snotes = getIntent().getStringExtra("note");

        priority = spriority;

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upData.setText(snotes);
        binding.upTanggal.setText(stanggal);
        binding.upWaktu.setText(swaktu);

        if(spriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.ic_done_24);
        } else if(spriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.ic_done_24);
        } else if(spriority.equals("3")) {
            binding.redPriority.setImageResource(R.drawable.ic_done_24);
        }

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        sTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateNotesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date  = day+"/"+month+"/"+year;
                        sTanggal.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        sWaktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateNotesActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        sWaktu.setText(h + ":" + m);
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

        binding.updateNotesBtn.setOnClickListener(v -> {
            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSubtitle.getText().toString();
            String notes = binding.upData.getText().toString();
            String tanggal = binding.upTanggal.getText().toString();
            String waktu = binding.upWaktu.getText().toString();

            UpdateNotes(title, subtitle, notes, tanggal, waktu);
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes, String tanggal, String waktu) {

        Date date  = new Date();
        CharSequence sequence = DateFormat.format("dd/MM/yyyy hh:mm:ss", date).toString();

        Notes updateNotes  = new Notes();

        updateNotes.id = sid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesTanggal = tanggal;
        updateNotes.notesWaktu = waktu;
        updateNotes.notes = notes;

        notesViewModel.updateNote(updateNotes);

        Toast.makeText(this,"Updated Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.ic_delete){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotesActivity.this).inflate
                    (R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottom_sheet));

            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);

            yes.setOnClickListener(v -> {
                notesViewModel.deleteNote(sid);
                finish();
            });

            no.setOnClickListener(v -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();
        }
        return true;
    }
}