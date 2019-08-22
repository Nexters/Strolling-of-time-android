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
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class UserServiceRetrofitTest {

  private String TAG = getClass().getSimpleName();
  // TODO: mocking

  private UserService userService;
  private AuthService authService;
  Long userId;

  @Before
  public void setUp() {

    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    userService = retrofit.create(UserService.class);
    authService = retrofit.create(AuthService.class);

    // "Bearer " + auth.getToken()
    // "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiaXNzIjoiY2xvY2tJc1dhdGNoIiwiaWF0IjoxNTY2Mzk1NzEwLCJleHAiOjE1NjY0ODIxMTB9.jS4uS7Sa_JmkM6URjFAjJor-r0NvcgPfNtb-0pydIIQ"
  }

  /* UserService Test */

  @Test
  public void 회원가입_탈퇴_Test() {
    // 회원 가입 테스트
    UserRequestDto userRequestDto = new UserRequestDto(
            TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, ""
    );
    Call<User> userCall = userService.signUp(userRequestDto);
    Response<User> userResponse = null;
    Long userId = null;
    try {
      userResponse = userCall.execute();
      if(userResponse.isSuccessful()) {
        userId = userResponse.body().getId();
      }
    } catch (IOException e) {
      Log.e(TAG, "signUpRequestError : ", e);
      assert false;
    }
    assert userResponse.isSuccessful();

    // 토큰 테스트
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> authCall = authService.login(basicAuth);
    Response<Auth> authResponse = null;
    try {
      authResponse = authCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "authRequestError : ", e);
      assert false;
    }
    assert authResponse.isSuccessful();

    // 회원 탈퇴 테스트
    Call<Void> deleteCall = userService.deleteUserById("Bearer " + authResponse.body().getToken(), userId);
    Response<Void> deleteResponse;
    try {
      deleteResponse = deleteCall.execute();
      if(deleteResponse.isSuccessful()) {
        Log.d(TAG, "회원가입_탈퇴_Test: " + deleteResponse.code());
      }else{
        Log.d(TAG, "회원가입_탈퇴_Test: " + deleteResponse.errorBody());
      }
    } catch (IOException e) {
      Log.e(TAG, "deleteUserError : ", e);
      assert false;
    }
  }
  @Test
  public void 회원가입_수정_Test(){
    // 회원 가입 테스트
    UserRequestDto userRequestDto = new UserRequestDto(
            TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, ""
    );
    Call<User> userCall = userService.signUp(userRequestDto);
    Response<User> userResponse = null;
    Long userId = null;
    try {
      userResponse = userCall.execute();
      if(userResponse.isSuccessful()) {
        userId = userResponse.body().getId();
      }
    } catch (IOException e) {
      Log.e(TAG, "signUpRequestError : ", e);
      assert false;
    }
    assert userResponse.isSuccessful();

    // 토큰 테스트
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> authCall = authService.login(basicAuth);
    Response<Auth> authResponse = null;
    try {
      authResponse = authCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "authRequestError : ", e);
      assert false;
    }
    assert authResponse.isSuccessful();

    // 회원 수정 테스트
    // Todo updateRequestDto가 서버와 안맞나 봅니다.
    UserRequestDto updateRequestDto = new UserRequestDto(
            "Test" + TestConstants.EMAIL,
            "Test" + TestConstants.PASSWORD,
            "Test" + TestConstants.NICKNAME,
            "Test");
    Call<User> updateCall = userService.patchUser(
            "Bearer " + authResponse.body().getToken(),
            userId,
            updateRequestDto);
    Response<User> updateResponse;
    try {
      updateResponse = updateCall.execute();
      if(updateResponse.isSuccessful()){
        Log.e(TAG, "회원가입_수정_Test: " + updateResponse.code());
        Log.e(TAG, "회원가입_수정_Test: " + updateResponse.body().getEmail());
      }else{
        Log.e(TAG, "회원가입_수정_Test: " + updateResponse.errorBody());
      }
    } catch (IOException e) {
      Log.e(TAG, "updateUserError : ", e);
      assert false;
    }
  }

  @Test
  public void getUserListTest(){
    // 토큰 가져오기
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> authCall = authService.login(basicAuth);
    Response<Auth> authResponse = null;
    try {
      authResponse = authCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "authRequestError : ", e);
      assert false;
    }
    assert authResponse.isSuccessful();

    // 유저들 모든 정보 가져오기 테스트
    /*
    Todo 지금 유저에 대한 모든 정보를 들고오려고 하는 API 인 것 같은데,
    Todo 이메일하고 닉넴임을 넣으면 넣은 유저 정보를 1개만 들고옵니다.
    */
    Call<List<User>> userListCall = userService.getUsers(
            "Bearer " + authResponse.body().getToken(),
            TestConstants.EMAIL,
            TestConstants.NICKNAME
    );
    Response<List<User>> userListResponse;
    try {
      userListResponse = userListCall.execute();
      if(userListResponse.isSuccessful()){
        for(int i = 0 ; i<userListResponse.body().size(); i++){
          Log.d(TAG, "getUserListTest: " + userListResponse.body().get(i).getEmail());
        }
      }else{
        Log.d(TAG, "getUserListTest: getUsersError");
      }
    } catch (IOException e) {
      Log.d(TAG, "getUserListTest: ", e);
      assert false;
    }
  }

  @Test
  public void getUserByUserIdTest(){
    // 회원 가입 테스트
    UserRequestDto userRequestDto = new UserRequestDto(
            TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, ""
    );
    Call<User> userCall = userService.signUp(userRequestDto);
    Response<User> userResponse = null;
    Long userId = null;
    try {
      userResponse = userCall.execute();
      if(userResponse.isSuccessful()) {
        userId = userResponse.body().getId();
      }
    } catch (IOException e) {
      Log.e(TAG, "signUpRequestError : ", e);
      assert false;
    }
    assert userResponse.isSuccessful();

    // 토큰 가져오기
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> authCall = authService.login(basicAuth);
    Response<Auth> authResponse = null;
    try {
      authResponse = authCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "authRequestError : ", e);
      assert false;
    }
    assert authResponse.isSuccessful();

    // 유저 정보 가져오기
    Call<User> userListCall = userService.getUserById(
            "Bearer " + authResponse.body().getToken(),
            userId
    );
    Response<User> getUserResponse;
    try {
      getUserResponse = userListCall.execute();
      if(getUserResponse.isSuccessful()){
        Assert.assertEquals(TestConstants.EMAIL, getUserResponse.body().getEmail());
      }else{
        Log.d(TAG, "getUserByUserIdTest: getUserError");
      }
    } catch (IOException e) {
      Log.d(TAG, "getUserByUserIdTest: ", e);
      assert false;
    }
  }




//  @Test
//  public void 회원가입_탈퇴_테스트() {
//    // TODO: JWT local generate mocking
//    UserRequestDto userRequestDto = new UserRequestDto(
//            TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, "");
//    Call<User> userResponse = userService.signUp(userRequestDto);
//    Response<User> user;
//
//    userId = 0L;
//    try {
//      user = userResponse.execute();
//      if(user.isSuccessful()) {
//        assert user.body() != null;
//        userId = user.body().getId();
//        Log.v(TAG, "회원가입_테스트 success: " + userId);
//      } else {
//        assert user.errorBody() != null;
//        Log.e(TAG, "회원가입_테스트 failed: " + user.errorBody().string());
//      }
//    } catch (Exception e) {
//      Log.e(TAG, "회원가입_테스트: ", e);
//      Assert.fail();
//    }
//  }
//
//  @Test
//  public void 유저검색_테스트() {
//
//  }
//
//  @Test
//  public void 회원탈퇴_테스트() {
//    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
//    Call<Auth> authCall = authService.login(basicAuth);
//    Response<Auth> authResponse = null;
//    try {
//      authResponse = authCall.execute();
//    } catch (IOException e) {
//      Log.e(TAG, "getJWT: ", e);
//      assert false;
//    }
//    assert authResponse.isSuccessful();
//
//    Call<Void> deleteResponse = userService.deleteUserById("Bearer " + authResponse.body().getToken(), userId);
//    Response<Void> noContent;
//    try {
//      noContent = deleteResponse.execute();
//      if(noContent.isSuccessful()) {
//        assert noContent.body() != null;
//        Log.d(TAG, "회원가입_테스트: " + noContent.code());
//      } else {
//        Log.d(TAG, "회원가입_테스트: " + noContent.errorBody());
//      }
//    } catch (Exception e) {
//      Log.e(TAG, "회원탈퇴_테스트: ", e);
//      Assert.fail();
//    }
//  }
}
