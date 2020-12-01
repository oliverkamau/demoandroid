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


public class ReceiptFragment extends Fragment {

    RecyclerView recyclerView;
    ReceiptAdapter adapter;
    View v;
    List<ReceiptBean> receiptBeans;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "token";
    private static final String ARG_PARAM2 = "policy";

    // TODO: Rename and change types of parameters
    private Long policy;
    private String token;

    public ReceiptFragment() {

    }


    public static ReceiptFragment newInstance(Long policy, String token) {
        ReceiptFragment fragment = new ReceiptFragment();
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
        View v= inflater.inflate(R.layout.fragment_receipt, container, false);
        recyclerView= v.findViewById(R.id.receipt_recycler);
        setRecyclerView(policy,token);
        return v;

    }

    private void setRecyclerView(Long policy, String token) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReceiptAdapter(getContext(), getList(policy,token));
        recyclerView.setAdapter(adapter);
    }

    private List<ReceiptBean> getList(Long policy, String token) {

        receiptBeans = new ArrayList<>();

        Call<List<ReceiptBean>> loginResponseCall = ApiClient.getPolicyService()
                .receiptBean(token, policy);
        loginResponseCall.enqueue(new Callback<List<ReceiptBean>>() {
            @Override
            public void onResponse(Call<List<ReceiptBean>> call, Response<List<ReceiptBean>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null || !response.body().isEmpty()) {
                        receiptBeans.addAll(response.body());
                        setMyRecyclerView(receiptBeans);
                        //Toast.makeText(Policies.this, policyBeans.get(1).getPolicyNo(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ReceiptBean>> call, Throwable t) {
                System.out.println("Error "+t.getLocalizedMessage());
            }
        });

        return receiptBeans;

    }

    private void setMyRecyclerView(List<ReceiptBean> receiptBeans) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReceiptAdapter(getContext(), receiptBeans);
        recyclerView.setAdapter(adapter);

    }
}