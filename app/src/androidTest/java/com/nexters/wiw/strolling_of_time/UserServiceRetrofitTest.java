package com.nexters.wiw.strolling_of_time;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import com.nexters.wiw.strolling_of_time.domain.Auth;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.User;
import com.nexters.wiw.strolling_of_time.dto.UserResponseDto;
import com.nexters.wiw.strolling_of_time.domain.UserService;
import com.nexters.wiw.strolling_of_time.dto.UserRequestDto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import retrofit2.Call;
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

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
        authService = retrofit.create(AuthService.class);

        // "Bearer " + auth.getToken()
        // "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QHRlc3QuY29tIiwiaXNzIjoiY2xvY2tJc1dhdGNoIiwiaWF0IjoxNTY2Mzk1NzEwLCJleHAiOjE1NjY0ODIxMTB9.jS4uS7Sa_JmkM6URjFAjJor-r0NvcgPfNtb-0pydIIQ"
    }

    @Test
    public void 그룹_테스트를_위한_계정_생성(){
        // 회원 가입 테스트
        UserRequestDto userRequestDto = new UserRequestDto(
                TestConstants.GROUP_TEST_EMAIL, TestConstants.GROUP_TEST_PASSWORD, TestConstants.GROUP_TEST_NICKNAME, ""
        );
        Call<User> createUserCall = userService.signUp(userRequestDto);
        Response<User> createUserRes = null;
        try {
            createUserRes = createUserCall.execute();
            assert createUserRes.isSuccessful();
            Log.d(TAG, "그룹_테스트를_위한_계정_생성: " + createUserRes.body().getId());
        } catch (IOException e) {
            Log.e(TAG, "signUpRequestError : ", e);
            assert false;
        }
        Assert.assertNotNull(createUserRes);

        // 토큰 테스트
        String basicAuth = Auth.basicAuthHeaderOf(TestConstants.GROUP_TEST_EMAIL, TestConstants.GROUP_TEST_PASSWORD);
        Call<Auth> authCall = authService.login(basicAuth);
        Response<Auth> authRes = null;
        try {
            authRes = authCall.execute();
            assert authRes.isSuccessful();
            Log.d(TAG, "그룹_테스트를_위한_계정_생성: "+authRes.body().getToken());
        } catch (IOException e) {
            Log.e(TAG, "authRequestError : ", e);
            assert false;
        }
        Assert.assertNotNull(authRes);
    }

    @Test
    public void 회원가입_가져오기_수정_삭제_테스트() {
        // 회원 가입 테스트
        UserRequestDto userRequestDto = new UserRequestDto(
                TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, ""
        );
        Call<User> createUserCall = userService.signUp(userRequestDto);
        Response<User> createUserRes = null;
        Long userId = null;
        try {
            createUserRes = createUserCall.execute();
            assert createUserRes.isSuccessful();
            userId = createUserRes.body().getId();
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + createUserRes.body().getId());
        } catch (IOException e) {
            Log.e(TAG, "signUpRequestError : ", e);
            assert false;
        }
        Assert.assertNotNull(userId);
        Assert.assertNotNull(createUserRes);



        // 토큰 테스트
        String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
        Call<Auth> authCall = authService.login(basicAuth);
        Response<Auth> authRes = null;
        try {
            authRes = authCall.execute();
            assert authRes.isSuccessful();
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: "+authRes.body().getToken());
        } catch (IOException e) {
            Log.e(TAG, "authRequestError : ", e);
            assert false;
        }
        Assert.assertNotNull(authRes);



        // 회원 가져오기 테스트
        Call<User> getUserCall = userService.getUserById("Bearer " + authRes.body().getToken(), userId);
        Response<User> getUserRes = null;
        try {
            getUserRes = getUserCall.execute();
            assert getUserRes.isSuccessful();
            Assert.assertEquals(TestConstants.NICKNAME, getUserRes.body().getNickname());
            Assert.assertEquals(TestConstants.EMAIL, getUserRes.body().getEmail());
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + getUserRes.body().getNickname());
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + getUserRes.body().getEmail());
        } catch (IOException e) {
            Log.e(TAG, "회원가입_가져오기_수정_삭제_테스트: ", e);
            Assert.fail();
        }
        Assert.assertNotNull(getUserRes);



        // 회원들 가져오기 테스트
        Call<UserResponseDto> getUsersCall = userService.getUsers(
                "Bearer " + authRes.body().getToken(),
                    TestConstants.EMAIL
                );
        Response<UserResponseDto> getUsersRes = null;
        try {
            getUsersRes = getUsersCall.execute();
            assert getUsersRes.isSuccessful();
            UserResponseDto users = getUsersRes.body();
            for(int i = 0 ; i < users.getUsers().size(); i++){
                UserResponseDto.User user = users.getUsers().get(i);
                Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: "+ user.getId());
                Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: "+ user.getNickname());
                Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: "+ user.getEmail());
                Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: "+ user.getProfileImage());
                Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: "+ user.getCreatedTime());
            }
            UserResponseDto.PageMetadata pageMetadata = users.getPageMetadata();
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + pageMetadata.getNumber());
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + pageMetadata.getSize());
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + pageMetadata.getTotalElements());
            Log.d(TAG, "회원가입_가져오기_수정_삭제_테스트: " + pageMetadata.getTotalPages());
        } catch (IOException e) {
            Log.e(TAG, "회원가입_가져오기_수정_삭제_테스트: ", e);
            Assert.fail();
        }
        Assert.assertNotNull(getUsersRes);



        // 회원 수정 테스트
        userRequestDto = new UserRequestDto(
                "Update" + TestConstants.EMAIL,
                "Update" + TestConstants.PASSWORD,
                "Update" + TestConstants.NICKNAME, ""
        );
        Call<User> updateUserCall = userService.patchUser("Bearer " + authRes.body().getToken(),
                userId,
                userRequestDto
        );
        Response<User> updateUserRes = null;
        try {
            updateUserRes = updateUserCall.execute();
            assert updateUserRes.isSuccessful();
        } catch (IOException e) {
            Log.e(TAG, "회원가입_가져오기_수정_삭제_테스트: ", e);
            Assert.fail();
        }
        Assert.assertNotNull(updateUserRes);



        // 회원 삭제 테스트
        Call<Void> deleteUserCall = userService.deleteUserById("Bearer " + authRes.body().getToken(), userId);
        Response<Void> deleteUserRes = null;
        try {
            deleteUserRes = deleteUserCall.execute();
            assert deleteUserRes.isSuccessful();
            Log.d(TAG, "회원가입_테스트: " + deleteUserRes.body());
        } catch (Exception e) {
            Log.e(TAG, "회원탈퇴_테스트: ", e);
            Assert.fail();
        }
        Assert.assertNotNull(deleteUserRes);
    }
}
