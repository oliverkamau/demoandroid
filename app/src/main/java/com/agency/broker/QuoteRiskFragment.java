package com.agency.broker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuoteRiskFragment extends Fragment {


    RecyclerView recyclerView;
    RiskAdapter adapter;
    View v;
    List<RiskBean> riskBeans;

    private static final String ARG_PARAM1 = "quote";
    private static final String ARG_PARAM2 = "token";

    private Long quote;
    private String token;

    public QuoteRiskFragment() {
    }


    public static QuoteRiskFragment newInstance(Long quote, String token) {
        QuoteRiskFragment fragment = new QuoteRiskFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, quote);
        args.putString(ARG_PARAM2, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quote = getArguments().getLong(ARG_PARAM1);
            token = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_quote_risk, container, false);
        recyclerView= v.findViewById(R.id.quote_risk_recycler);
        setRecyclerView(quote,token);
        return v;
    }

    private void setRecyclerView(Long quoteId,String token) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RiskAdapter(getContext(), getList(quoteId,token));
        recyclerView.setAdapter(adapter);
    }

    private List<RiskBean> getList(Long quoteId,String token) {
        riskBeans = new ArrayList<>();

        Call<List<RiskBean>> loginResponseCall = ApiClient.getPolicyService()
                .quoterisks(token, quoteId);
        loginResponseCall.enqueue(new Callback<List<RiskBean>>() {
            @Override
            public void onResponse(Call<List<RiskBean>> call, Response<List<RiskBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null || !response.body().isEmpty()) {
                        riskBeans.addAll(response.body());
                        setMyRecyclerView(riskBeans);
                        //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RiskBean>> call, Throwable t) {
                System.out.println("Error "+t.getLocalizedMessage());
            }
        });

        return riskBeans;
    }

    private void setMyRecyclerView(List<RiskBean> riskBeanList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RiskAdapter(getContext(), riskBeanList);
        recyclerView.setAdapter(adapter);
    }
}