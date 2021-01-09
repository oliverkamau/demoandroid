package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelfQuote extends AppCompatActivity {

    TextView tv;
    RecyclerView recyclerView;
    SelfQuoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_quote);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i=getIntent();
         tv=findViewById(R.id.allquotes);
         long quotes=i.getLongExtra("quotes",0);

        String cover=i.getStringExtra("cover");
        String period=i.getStringExtra("period");
        String startDate=i.getStringExtra("startDate");
        String engine=i.getStringExtra("engine");
        String value=i.getStringExtra("value");
        String model=i.getStringExtra("model");
        String year=i.getStringExtra("year");
        String vehicle=i.getStringExtra("vehicle");
         MotorFormBean formBean=new MotorFormBean();
         formBean.setVehicle(vehicle);
         formBean.setModelYear(year);
         formBean.setValue(value);
         formBean.setEngine(engine);
         formBean.setModel(model);
         formBean.setStartDate(startDate);
         formBean.setCoverLength(period);
         formBean.setCover(cover);

         tv.setText("All Quotes: "+quotes);
        recyclerView = findViewById(R.id.selfquotesrecycler);
        setRecyclerView(formBean);
    }
    private void setRecyclerView(MotorFormBean formBean) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        adapter = new SelfQuoteAdapter(this, getList(formBean), new SelfQuoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelfQuoteBean item) {

                startActivity(new Intent(SelfQuote.this, SelfQuoteDetails.class).putExtra("quoteId", item.getSelfQuoteId()));            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setMyRecyclerView(List<SelfQuoteBean> beans) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        adapter = new SelfQuoteAdapter(this, beans, new SelfQuoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SelfQuoteBean item) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SelfQuote.this, SelfQuoteDetails.class).putExtra("quoteId", item.getSelfQuoteId()));
                    }
                }, 1000);
            }
        });
        recyclerView.setAdapter(adapter);
    }


    private List<SelfQuoteBean> getList(MotorFormBean formBean) {
        List<SelfQuoteBean> selfQuoteBeans = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);

        String token = sharedPreferences.getString("token", "token");
        token = "Bearer " + token;
        Call<List<SelfQuoteBean>> loginResponseCall = ApiClient.getPolicyService()
                .selfqouotes(token,formBean);
        loginResponseCall.enqueue(new Callback<List<SelfQuoteBean>>() {
            @Override
            public void onResponse(Call<List<SelfQuoteBean>> call, Response<List<SelfQuoteBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null || !response.body().isEmpty()) {
                        selfQuoteBeans.addAll(response.body());
                        setMyRecyclerView(selfQuoteBeans);
                        //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SelfQuoteBean>> call, Throwable t) {
                Toast.makeText(SelfQuote.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return selfQuoteBeans;
    }
}