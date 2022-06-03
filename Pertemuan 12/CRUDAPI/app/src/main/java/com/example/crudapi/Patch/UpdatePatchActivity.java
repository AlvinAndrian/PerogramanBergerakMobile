package com.example.crudapi.Patch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapi.Put.PutResponse;
import com.example.crudapi.R;
import com.example.crudapi.RestClient;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePatchActivity extends AppCompatActivity {

    EditText patchName, patchJob;
    Button UpdatePatch;
    TextView name, job, updateJob, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patch);
        ButterKnife.bind(this);

        patchName = findViewById(R.id.patchName);
        patchJob = findViewById(R.id.patchJob);
        UpdatePatch = findViewById(R.id.donePatch);
        name = findViewById(R.id.textViewNamePatch);
        job = findViewById(R.id.textViewJobPatch);
        updateJob = findViewById(R.id.textViewCreateAtPatch);
        status = findViewById(R.id.textViewStatusPatch);

        UpdatePatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.getService().updatePatch(patchName.getText().toString(), patchJob.getText().toString()).enqueue(new Callback<PatchResponse>() {
                    @Override
                    public void onResponse(Call<PatchResponse> call, Response<PatchResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {

                                status.setText(Integer.toString(response.code()));
                                name.setText(response.body().getName());
                                job.setText(response.body().getJob());
                                updateJob.setText(response.body().getUpdatedAt());
                            }
                            Toast.makeText(getApplicationContext(),
                                    "Name :" + response.body().getName() +
                                            "Job :" + response.body().getJob(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PatchResponse> call, Throwable t) {

                    }
                });
            }
        });


    }
}