package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Quotes extends AppCompatActivity {

    RecyclerView recyclerView;
    QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quotes);
        recyclerView = findViewById(R.id.quotesrecycler);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        adapter = new QuotesAdapter(this, getList(),  new QuotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QuotesBean item) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Quotes.this, QuoteDetails.class).putExtra("quoteId", item.getQuoteId()));
                    }
                }, 1000);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setMyRecyclerView(List<QuotesBean> beans) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        adapter = new QuotesAdapter(this, beans,  new QuotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QuotesBean item) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Quotes.this, QuoteDetails.class).putExtra("quoteId", item.getQuoteId()));
                    }
                }, 1000);
            }
        });
        recyclerView.setAdapter(adapter);
    }


    private List<QuotesBean> getList() {
        List<QuotesBean> quotesBeans = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
        Long clid = sharedPreferences.getLong("client", 0);
        String token = sharedPreferences.getString("token", "token");
        token = "Bearer " + token;
        Call<List<QuotesBean>> loginResponseCall = ApiClient.getQuotes().quotesBean(token, clid);
        loginResponseCall.enqueue(new Callback<List<QuotesBean>>() {
            @Override
            public void onResponse(Call<List<QuotesBean>> call, Response<List<QuotesBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null || !response.body().isEmpty()) {
                        quotesBeans.addAll(response.body());
                        setMyRecyclerView(quotesBeans);
                        //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<QuotesBean>> call, Throwable t) {
                Toast.makeText(Quotes.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return quotesBeans;
    }
}