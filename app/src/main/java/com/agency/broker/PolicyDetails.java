package com.agency.broker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PolicyDetails extends AppCompatActivity {

       TextView t1,t2,t3,t4,t5,t6,t7;
       TabLayout tabLayout;
       ViewPager viewPager;
       CleanserAdapter adapter;
       String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_policy_details);
        Intent intent=getIntent();
        long policyId=0L;
        if(intent.getExtras()!=null){
            SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
             token = sharedPreferences.getString("token", "token");
            token = "Bearer " + token;
            policyId=intent.getLongExtra("policyId",0);
        }
        setPolicyDetailsView(policyId);
        tabLayout=(TabLayout) findViewById(R.id.tabs_layout);
        viewPager=(ViewPager) findViewById(R.id.viewpager_id);
        adapter= new CleanserAdapter(getSupportFragmentManager());

        adapter.addFragment(new RiskFragment(),token,policyId);
        adapter.addFragment(new ReceiptFragment(),token,policyId);
        adapter.addFragment(new ClaimsFragment(),token,policyId);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("Risks");
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText("Receipts");
        Objects.requireNonNull(tabLayout.getTabAt(2)).setText("Claims");

    }

    private void setPolicyDetailsView(Long policyId) {
        SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "token");
        token = "Bearer " + token;
        Call<PolicyDetailsBean> loginResponseCall = ApiClient.getPolicyService()
                .policyDetailsBean(token, policyId);
        loginResponseCall.enqueue(new Callback<PolicyDetailsBean>() {
            @Override
            public void onResponse(Call<PolicyDetailsBean> call, Response<PolicyDetailsBean> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        t7=findViewById(R.id.prodvalue);
                        t7.setText(response.body().getProduct());
                       t1=findViewById(R.id.textView11);
                       t1.setText(response.body().getPolicyNo());
                       t2=findViewById(R.id.textView13);
                       t2.setText(response.body().getStatus());
                       t3=findViewById(R.id.textView15);
                       t3.setText(response.body().getCoverFrom());
                       t4=findViewById(R.id.textView17);
                       t4.setText(response.body().getCoverTo());
                       t5=findViewById(R.id.textView19);
                       t5.setText(response.body().getRenewalDate());
                       t6=findViewById(R.id.textView21);
                       t6.setText(response.body().getBalance());
                       t7=findViewById(R.id.prodvalue);
                       t7.setText(response.body().getProduct());
                    }
                }
            }

            @Override
            public void onFailure(Call<PolicyDetailsBean> call, Throwable t) {
                Toast.makeText(PolicyDetails.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}