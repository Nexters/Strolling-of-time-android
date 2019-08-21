package com.nexters.wiw.strolling_of_time;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import com.auth0.android.jwt.DecodeException;
import com.auth0.android.jwt.JWT;
import com.nexters.wiw.strolling_of_time.domain.Auth;
import com.nexters.wiw.strolling_of_time.domain.AuthService;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(AndroidJUnit4.class)
public class AuthServiceRetrofitTest {
  private String TAG = getClass().getSimpleName();
  private AuthService service;

  @Before
  public void setUp() {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    service = retrofit.create(AuthService.class);
  }

  @Test
  public void 로그인_테스트() {
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> auth = service.login(basicAuth);
    auth.enqueue(new Callback<Auth>() {
      @Override
      public void onResponse(
              @NotNull Call<Auth> call,
              @NotNull Response<Auth> response) {

        if(response.isSuccessful()) {

          assert response.body() != null;

          String token = response.body().getToken();

          try {
            new JWT(token);
          } catch (DecodeException e) {
            Log.e(TAG, "JWT decode: ", e);
            assert false;
          }
        }
      }

      @Override
      public void onFailure(
              @NotNull Call<Auth> call,
              @NotNull Throwable t) {
        Log.d(TAG, "onFailure: " + t.getMessage());
        assert false;
      }
    });
  }
}
