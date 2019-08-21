package com.nexters.wiw.strolling_of_time.domain;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthService {
  @POST("auth")
  Call<Auth> login(@Header("Authorization") String basicAuth);

}
