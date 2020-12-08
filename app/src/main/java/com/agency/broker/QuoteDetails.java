package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteDetails extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7;
    TabLayout tabLayout;
    ViewPager viewPager;
    QuotePagerAdapter adapter;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_details);
        Intent intent=getIntent();
        long quoteId=0L;
        if(intent.getExtras()!=null){
            SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
            token = sharedPreferences.getString("token", "token");
            token = "Bearer " + token;
            quoteId=intent.getLongExtra("quoteId",0);
        }
        setQuoteDetailsView(quoteId);
        tabLayout=(TabLayout) findViewById(R.id.quote_tabs_layout);
        viewPager=(ViewPager) findViewById(R.id.quoteviewpager_id);
        adapter= new QuotePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new QuoteRiskFragment(),token,quoteId);


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("Risks");

    }

    private void setQuoteDetailsView(Long policyId) {
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "token");
        token = "Bearer " + token;
        Call<QuoteDetailsBean> loginResponseCall = ApiClient.getPolicyService()
                .quoteDetailsBean(token, policyId);
        loginResponseCall.enqueue(new Callback<QuoteDetailsBean>() {
            @Override
            public void onResponse(Call<QuoteDetailsBean> call, Response<QuoteDetailsBean> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        t7=findViewById(R.id.quoteprodvalue);
                        t7.setText(response.body().getProduct());
                        t1=findViewById(R.id.quoteNoValue);
                        t1.setText(response.body().getQuoteNo());
                        t2=findViewById(R.id.statusValue);
                        t2.setText(response.body().getStatus());
                        t3=findViewById(R.id.quoteFrom);
                        t3.setText(response.body().getCoverFrom());
                        t4=findViewById(R.id.quoteTo);
                        t4.setText(response.body().getCoverTo());
                        t5=findViewById(R.id.quoteExpiryValue);
                        t5.setText(response.body().getExpiry());
                        t6=findViewById(R.id.quoteAmmountValue);
                        t6.setText(response.body().getAmmount());

                    }
                }
            }

            @Override
            public void onFailure(Call<QuoteDetailsBean> call, Throwable t) {
                Toast.makeText(QuoteDetails.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}