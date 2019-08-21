package com.nexters.wiw.strolling_of_time;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.auth0.android.jwt.DecodeException;
import com.auth0.android.jwt.JWT;
import com.nexters.wiw.strolling_of_time.domain.Auth;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.User;
import com.nexters.wiw.strolling_of_time.domain.UserService;
import com.nexters.wiw.strolling_of_time.dto.UserRequestDto;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(AndroidJUnit4.class)
public class UserServiceRetrofitTest {

  private String TAG = getClass().getSimpleName();
  // TODO: mocking

  private UserService userService;
  private AuthService authService;
  private SharedPreferences sharedPref;
  @Before
  public void setUp() {

    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    userService = retrofit.create(UserService.class);
    authService = retrofit.create(AuthService.class);

    sharedPref = context
            .getSharedPreferences(TestConstants.JWT_PREF, Context.MODE_PRIVATE);
    Auth auth = getJWT();
  }

  private Auth getJWT() {
    // TODO: JWT local generate mocking
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> auth = authService.login(basicAuth);
    Response<Auth> response = null;
    try {
      response = auth.execute();
    } catch (IOException e){
      Log.e(TAG, "getJWT: ", e);
      assert false;
    }
    assert response.isSuccessful();
    return response.body();
  }

  @Test
  public void 회원가입_테스트() {
    UserRequestDto userRequestDto = new UserRequestDto(
            TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, "");
    Call<User> userResponse = userService.signUp(userRequestDto);
    userResponse.enqueue(new Callback<User>() {
      @Override
      public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
        Log.d(this.getClass().getSimpleName(), "onResponse: " + response);
        if(response.isSuccessful()) {
          assert response.body() != null;
          Log.d(this.getClass().getSimpleName(), "onResponse: " + response.body().getId());
        }
      }

      @Override
      public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
        Log.d(getClass().getName(), "onFailure: " + t.getMessage());
        assert false;
      }
    });
  }

  @Test
  public void 유저검색_테스트(){

  }
}
