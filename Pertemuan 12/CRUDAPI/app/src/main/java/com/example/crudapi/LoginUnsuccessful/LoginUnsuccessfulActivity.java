package com.example.crudapi.LoginUnsuccessful;

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

public class LoginUnsuccessfulActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_unsuccessful);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {

        BodyLoginUnsuccessful bodyLoginUnsuccessful =  new BodyLoginUnsuccessful();
        bodyLoginUnsuccessful.setEmail(edtEmail.getText().toString());


        RestClient.getService().postUnsuccessLogin(bodyLoginUnsuccessful).enqueue(new Callback<LoginUnsuccessfulResponse>() {
            // TODO method dibawah otomatis pada saat new Callback
            @Override
            public void onResponse(Call<LoginUnsuccessfulResponse> call, Response<LoginUnsuccessfulResponse> response) {
                Toast.makeText(getApplicationContext(),"error : Missing password",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginUnsuccessfulResponse> call, Throwable t) {

            }
        });
    }
}