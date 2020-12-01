package com.agency.broker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextInputEditText username,password;
    Button btnLogin;
    public static final String CLIENT_STORAGE = "insurance";
    public static final String CL_ID = "client";
    public static final String ACCESS_TOKEN= "token";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        username=(TextInputEditText)findViewById(R.id.username);
        password=(TextInputEditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.extended_fab);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Call<LoginResponse> loginResponseCall=ApiClient.grtUserService()
                .userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    SharedPreferences sharedPreferences= getSharedPreferences(CLIENT_STORAGE,MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    assert response.body() != null;
                    editor.putLong(CL_ID,response.body().getClientId());
                    editor.putString(ACCESS_TOKEN,response.body().getJwt());
                    editor.apply();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(Login.this,Home.class).putExtra("name",response.body().getRealName()));
                        }
                    },1000);

                }
                else {
                    StyleableToast.makeText(Login.this, "Invalid Login Credentials!", R.style.mytoast).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this,"Throwable "+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}