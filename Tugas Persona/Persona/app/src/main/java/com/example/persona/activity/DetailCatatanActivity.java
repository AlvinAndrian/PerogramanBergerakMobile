package com.example.persona.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.persona.R;
import com.example.persona.model.ListModel;

public class DetailCatatanActivity extends AppCompatActivity {
    private ListModel catatan;
    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_catatan);

        TextView tvName = findViewById(R.id.title_detail);
        TextView tvTanggal = findViewById(R.id.tgl_detail);
        TextView tvDetail = findViewById(R.id.catatan_detail);

        catatan = getIntent().getParcelableExtra(ITEM_EXTRA);

        if(catatan != null) {
            tvName.setText(catatan.getName());
            Log.e("LAT", String.valueOf(catatan.getLatitude()));
            Log.e("LNG", String.valueOf(catatan.getLongitude()));
            tvTanggal.setText(catatan.getTanggal());
            tvDetail.setText(catatan.getDetail());
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Catatan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}