package com.agency.broker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiskFragment extends Fragment {

    RecyclerView recyclerView;
    RiskAdapter adapter;
    View v;
    List<RiskBean> riskBeanList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "policy";
    private static final String ARG_PARAM2 = "token";

    // TODO: Rename and change types of parameters
    private Long policy;
    private String token;




    public static RiskFragment newInstance(Long policy, String token) {
        RiskFragment fragment = new RiskFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, policy);
        args.putString(ARG_PARAM2, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            policy = getArguments().getLong(ARG_PARAM1);
            token = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_risk, container, false);
        recyclerView= v.findViewById(R.id.risk_recycler);
        setRecyclerView(policy,token);
        return v;
    }
    private void setRecyclerView(Long policyId,String token) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RiskAdapter(getContext(), getList(policyId,token));
        recyclerView.setAdapter(adapter);
    }

    private List<RiskBean> getList(Long policyId,String token) {
         riskBeanList = new ArrayList<>();

        Call<List<RiskBean>> loginResponseCall = ApiClient.getPolicyService()
                .riskBean(token, policyId);
        loginResponseCall.enqueue(new Callback<List<RiskBean>>() {
            @Override
            public void onResponse(Call<List<RiskBean>> call, Response<List<RiskBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null || !response.body().isEmpty()) {
                        riskBeanList.addAll(response.body());
                        setMyRecyclerView(riskBeanList);
                        //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RiskBean>> call, Throwable t) {
                System.out.println("Error "+t.getLocalizedMessage());
            }
        });

        return riskBeanList;
    }

    private void setMyRecyclerView(List<RiskBean> riskBeanList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RiskAdapter(getContext(), riskBeanList);
        recyclerView.setAdapter(adapter);
    }
}