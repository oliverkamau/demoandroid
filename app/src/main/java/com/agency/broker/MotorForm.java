package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.muddzdev.styleabletoast.StyleableToast;

import org.jetbrains.annotations.NotNull;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotorForm extends AppCompatActivity {

    Button button;
    TextInputEditText textInputEditText,engine,year,vehicle,model,value,period;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_motor_form);
        autoCompleteTextView=findViewById(R.id.covtyp);

        String[] options={"Comprehensive","Third Party Only"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,R.layout.cover_menu,options);
        autoCompleteTextView.setText(arrayAdapter.getItem(0),false);
        autoCompleteTextView.setAdapter(arrayAdapter);

        button=(Button)findViewById(R.id.getquote);
        value=findViewById(R.id.marketvalue);
        period=findViewById(R.id.coverlength);
        model=findViewById(R.id.makemodel);
        vehicle=findViewById(R.id.registration);
        engine=findViewById(R.id.enginecapacity);
        year=findViewById(R.id.manufactureyear);

        MaterialDatePicker.Builder material= MaterialDatePicker.Builder.datePicker();
        material.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker= material.build();

        textInputEditText=findViewById(R.id.fromyear);
        textInputEditText.setShowSoftInputOnFocus(false);
        textInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");


             /*   new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MotorForm.this,SelfQuote.class));
                    }
                },1000);          */
            }


        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                String pattern = "dd/MM/yyyy" ;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                String f=simpleDateFormat.format(selection);
                textInputEditText.setText(f);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MotorFormBean motorFormBean=new MotorFormBean();
                motorFormBean.setCover(autoCompleteTextView.getText().toString());
                motorFormBean.setCoverLength(period.getText().toString());
                motorFormBean.setStartDate(textInputEditText.getText().toString());
                motorFormBean.setEngine(engine.getText().toString());
                motorFormBean.setValue(value.getText().toString());
                motorFormBean.setModel(model.getText().toString());
                motorFormBean.setModelYear(year.getText().toString());
                motorFormBean.setVehicle(vehicle.getText().toString());

                findQuotes(motorFormBean);
             /*   new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MotorForm.this,SelfQuote.class));
                    }
                },1000);          */
            }


        });

    }

    private void findQuotes(MotorFormBean motorFormBean) {
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "token");
        token = "Bearer " + token;
        Call<QuoteCount> loginResponseCall=ApiClient.getPolicyService()
                .countQuotes(token,motorFormBean.getCover());
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
                            i.putExtra("cover", motorFormBean.getCover());
                            i.putExtra("period", motorFormBean.getCoverLength());
                            i.putExtra("startDate",motorFormBean.getStartDate());
                            i.putExtra("engine",motorFormBean.getEngine());
                            i.putExtra("value",motorFormBean.getValue());
                            i.putExtra("model",motorFormBean.getModel());
                            i.putExtra("year",motorFormBean.getModelYear());
                            i.putExtra("vehicle",motorFormBean.getVehicle());
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



