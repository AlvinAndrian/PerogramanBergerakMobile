package com.example.crudapi.SingleUsersNotFound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.crudapi.R;
import com.example.crudapi.RestClient;
import com.example.crudapi.SingleUsers.SingleResponse;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleUsersNFActivity extends AppCompatActivity {
    TextView responseStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_users_nfactivity);
        ButterKnife.bind(this);
        responseStatus = findViewById(R.id.responseStatusSingleNotFound);

        RestClient.getService().getSingleNotFound().enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(@NonNull Call<SingleResponse> call, @NonNull Response<SingleResponse> response) {
                if (response.isSuccessful()){
                    responseStatus.setText(Integer.toString(response.code()));
                } else {
                    responseStatus.setText(Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<SingleResponse> call, @NonNull Throwable t) {

            }
        });
    }
}