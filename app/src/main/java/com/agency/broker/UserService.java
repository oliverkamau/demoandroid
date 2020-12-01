package com.agency.broker;


import android.content.SharedPreferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

  @POST("authenticate")
  Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);


}
