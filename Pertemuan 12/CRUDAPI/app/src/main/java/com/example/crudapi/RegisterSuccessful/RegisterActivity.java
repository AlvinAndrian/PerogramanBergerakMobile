package com.example.crudapi.RegisterSuccessful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudapi.LoginSuccessful.BodyLogin;
import com.example.crudapi.LoginSuccessful.LoginActivity;
import com.example.crudapi.LoginSuccessful.LoginResponse;
import com.example.crudapi.LoginSuccessful.LoginSuccessfulActivity;
import com.example.crudapi.R;
import com.example.crudapi.RestClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {

        BodyRegister bodyRegister =  new BodyRegister();
        bodyRegister.setEmail(edtEmail.getText().toString());
        bodyRegister.setPassword(edtPassword.getText().toString());


        RestClient.getService().postRegister(bodyRegister).enqueue(new Callback<RegisterResponse>() {
            // TODO method dibawah otomatis pada saat new Callback
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Toast.makeText(getApplicationContext(),
                        "ID : " + response.body().getId() +
                                "Token : " + response.body().getToken(),Toast.LENGTH_SHORT).show();
                Log.i("Response",response.message());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
}