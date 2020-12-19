package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotorForm extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_motor_form);

        button=(Button)findViewById(R.id.getquote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findQuotes();
             /*   new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MotorForm.this,SelfQuote.class));
                    }
                },1000);          */
            }


        });

    }

    private void findQuotes() {
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "token");
        token = "Bearer " + token;
        Call<QuoteCount> loginResponseCall=ApiClient.getPolicyService()
                .countQuotes(token);
        loginResponseCall.enqueue(new Callback<QuoteCount>() {
            @Override
            public void onResponse(@NotNull Call<QuoteCount> call, @NotNull Response<QuoteCount> response) {
                if(response.isSuccessful()){

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i=new Intent(MotorForm.this,SelfQuote.class);
                            assert response.body() != null;
                            i.putExtra("quotes",response.body().getQuoteCount());

                            startActivity(i);
                        }
                    },1000);

                }
                else {
                    StyleableToast.makeText(MotorForm.this, "Invalid Login Credentials!", R.style.mytoast).show();
                }
            }

            @Override
            public void onFailure(Call<QuoteCount> call, Throwable t) {
                Toast.makeText(MotorForm.this,"Throwable "+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
    }



