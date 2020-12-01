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

public class ClaimsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "policy";
    private static final String ARG_PARAM2 = "token";

    private Long policy;
    private String token;

    RecyclerView recyclerView;
    ClaimAdapter adapter;
    View v;
    List<ClaimsBean> claimsBeans;

    public ClaimsFragment() {

    }


    public static ClaimsFragment newInstance(Long policy, String token) {
        ClaimsFragment fragment = new ClaimsFragment();
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
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_claims, container, false);
        recyclerView= v.findViewById(R.id.claim_recycler);
        setRecyclerView(policy,token);
        return v;
    }

    private void setRecyclerView(Long policy, String token) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ClaimAdapter(getContext(), getList(policy,token));
        recyclerView.setAdapter(adapter);

    }

    private List<ClaimsBean> getList(Long policy, String token) {
        claimsBeans = new ArrayList<>();

        Call<List<ClaimsBean>> loginResponseCall = ApiClient.getPolicyService()
                .claimsBean(token, policy);
        loginResponseCall.enqueue(new Callback<List<ClaimsBean>>() {
            @Override
            public void onResponse(Call<List<ClaimsBean>> call, Response<List<ClaimsBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null || !response.body().isEmpty()) {
                        claimsBeans.addAll(response.body());
                        setMyRecyclerView(claimsBeans);
                        //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ClaimsBean>> call, Throwable t) {
                System.out.println("Error "+t.getLocalizedMessage());
            }
        });

        return claimsBeans;
    }

    private void setMyRecyclerView(List<ClaimsBean> claimsBeans) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ClaimAdapter(getContext(), claimsBeans);
        recyclerView.setAdapter(adapter);
    }
}