package com.agency.broker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PolicyService {

    @GET("policies/{code}")
    Call<List<PolicyBean>> policyBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("myquotes/{code}")
    Call<List<QuotesBean>> quotesBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("risks/{code}")
    Call<List<RiskBean>> riskBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("receipts/{code}")
    Call<List<ReceiptBean>> receiptBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("claims/{code}")
    Call<List<ClaimsBean>> claimsBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("policy/{code}")
    Call<PolicyDetailsBean> policyDetailsBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("quotedetails/{code}")
    Call<QuoteDetailsBean> quoteDetailsBean(@Header("Authorization") String header,@Path("code") Long code);
    @GET("quoterisks/{code}")
    Call<List<RiskBean>> quoterisks(@Header("Authorization") String header,@Path("code") Long code);

    @POST("selfquotes")
    Call<List<SelfQuoteBean>> selfqouotes(@Header("Authorization") String header,@Body MotorFormBean formBean);

    @GET("countQuotes/{cover}")
    Call<QuoteCount> countQuotes(@Header("Authorization") String header,@Path("cover") String cover);

    @GET("selfquotedetails/{code}")
    Call<List<SelfQuoteDetailsBean>> selfquotedetails(@Header("Authorization") String header,@Path("code") Long code);
}
