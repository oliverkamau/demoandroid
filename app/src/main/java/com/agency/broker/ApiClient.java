package com.agency.broker;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client= new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.43.145:8082/")
                .client(client)
                .build();
    }
    public static UserService grtUserService(){
        return getRetrofit().create(UserService.class);

    }
    public static PolicyService getPolicyService(){
        return getRetrofit().create(PolicyService.class);

    }

    public static PolicyService getPolicyDetails(){
        return getRetrofit().create(PolicyService.class);

    }

    public static PolicyService getQuotes(){
        return getRetrofit().create(PolicyService.class);

    }

}
