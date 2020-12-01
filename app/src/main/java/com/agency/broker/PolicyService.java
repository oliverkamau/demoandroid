package com.agency.broker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface PolicyService {

    @GET("policies/{code}")
    Call<List<PolicyBean>> policyBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("risks/{code}")
    Call<List<RiskBean>> riskBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("receipts/{code}")
    Call<List<ReceiptBean>> receiptBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("claims/{code}")
    Call<List<ClaimsBean>> claimsBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("policy/{code}")
    Call<PolicyDetailsBean> policyDetailsBean(@Header("Authorization") String header,@Path("code") Long code);
}
