package com.example.notebook.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
    String priority = "1";
    String alarm = "2";
    String stitle, ssubtitle, snotes, spriority, salarm, sreminder;
    NotesViewModel notesViewModel;
    int sid;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch upAlarm;
    TextView upReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        upReminder = findViewById(R.id.upReminder);
        upAlarm = findViewById(R.id.upAlarm);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        upReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        UpdateNotesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date  = day+"/"+month+"/"+year;
                        upReminder.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        sid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("note");
        salarm = getIntent().getStringExtra("alarm");
        sreminder = getIntent().getStringExtra("reminder");

        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upData.setText(snotes);
        binding.upReminder.setText(sreminder);

        if(spriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.ic_done_24);
        } else if(spriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.ic_done_24);
        } else if(spriority.equals("3")) {
            binding.redPriority.setImageResource(R.drawable.ic_done_24);
        }

        if(salarm.equals("1")){
            alarm = "1";
        } else if(salarm.equals("2")){
            alarm = "2";
        }

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

        upAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alarm ="1";
                } else {
                    alarm ="2";
                }
            }});

        binding.updateNotesBtn.setOnClickListener(v -> {
            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSubtitle.getText().toString();
            String notes = binding.upData.getText().toString();
            String reminder = binding.upReminder.getText().toString();

            UpdateNotes(title, subtitle, notes, reminder);
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes, String reminder) {

        Date date  = new Date();
        CharSequence sequence = DateFormat.format("dd/MM/yyyy hh:mm:ss", date).toString();

        Notes updateNotes  = new Notes();

        updateNotes.id = sid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notesPriority = priority;
        updateNotes.notesAlarm = alarm;
        updateNotes.notesReminder = reminder;
        updateNotes.notesDate = sequence.toString();
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