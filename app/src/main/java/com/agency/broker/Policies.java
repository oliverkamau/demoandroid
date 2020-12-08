package com.agency.broker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Policies extends AppCompatActivity {

    RecyclerView recyclerView;
    PolicyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policies);
        recyclerView = findViewById(R.id.policyrecycler);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        adapter = new PolicyAdapter(this, getList(), new PolicyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PolicyBean item) {

                Toast.makeText(Policies.this, item.getPolicyNo(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void setMyRecyclerView(List<PolicyBean> beans) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager l=new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(l);
        adapter = new PolicyAdapter(this, beans, new PolicyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PolicyBean item) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Policies.this, PolicyDetails.class).putExtra("policyId", item.getPolicyId()));
                    }
                }, 1000);
            }
        });
                recyclerView.setAdapter(adapter);
            }


            private List<PolicyBean> getList() {
                List<PolicyBean> policyBeans = new ArrayList<>();
                SharedPreferences sharedPreferences = getSharedPreferences("insurance", MODE_PRIVATE);
                Long clid = sharedPreferences.getLong("client", 0);
                String token = sharedPreferences.getString("token", "token");
                token = "Bearer " + token;
                Call<List<PolicyBean>> loginResponseCall = ApiClient.getPolicyService()
                        .policyBean(token, clid);
                loginResponseCall.enqueue(new Callback<List<PolicyBean>>() {
                    @Override
                    public void onResponse(Call<List<PolicyBean>> call, Response<List<PolicyBean>> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null || !response.body().isEmpty()) {
                                policyBeans.addAll(response.body());
                                setMyRecyclerView(policyBeans);
                                //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PolicyBean>> call, Throwable t) {
                        Toast.makeText(Policies.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                return policyBeans;
            }
        }
