package com.nexters.wiw.strolling_of_time;

import com.nexters.wiw.strolling_of_time.domain.GroupService;
import com.nexters.wiw.strolling_of_time.dto.GroupRequestDto;
import com.nexters.wiw.strolling_of_time.dto.LoginRequestDto;
import com.nexters.wiw.strolling_of_time.dto.LoginResponseDto;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.UserService;
import com.nexters.wiw.strolling_of_time.dto.UserModifyRequestDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpRequestDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpResponseDto;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Optional;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public final class HTTPServiceTest extends RetrofitResponseMixin {
    private static  UserService userService;
    private static  AuthService authService;
    private static  GroupService groupService;

    private static String tokenHeader;
    private static long userIdPathVariable;

    @BeforeClass
    public static void setUp() throws Throwable {
        // TODO: 통합 테스트 (with API container)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userService = retrofit.create(UserService.class);
        authService = retrofit.create(AuthService.class);
        groupService = retrofit.create(GroupService.class);

        Call<SignUpResponseDto> signUpRequest = userService.signUp(new SignUpRequestDto(
                TestConstants.EMAIL, TestConstants.PASSWORD, TestConstants.NICKNAME, ""
        ));
        Call<LoginResponseDto> loginRequest = authService.login(
                LoginRequestDto.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD));

        Response<SignUpResponseDto> signUpResponse = signUpRequest.execute();
        printResponseDetail(signUpResponse);

        Response<LoginResponseDto> loginResponse = loginRequest.execute();
        printResponseDetail(loginResponse);

        tokenHeader = getToken(loginResponse);
        userIdPathVariable = getUserId(signUpResponse);
    }

    @AfterClass
    public static void tearDown() throws Throwable{
        Call<Void> withdrawalRequest = userService.deleteUserById(tokenHeader, userIdPathVariable);
        Response<Void> withdrawalResponse = withdrawalRequest.execute();
        printResponseDetail(withdrawalResponse);
    }

    @Test
    public void A_UserServiceRequest_기능테스트() throws Throwable {
        UserModifyRequestDto updateRequestDto = UserModifyRequestDto.builder()
                .nickname("modify" + TestConstants.NICKNAME)
                .password("modify" + TestConstants.PASSWORD)
                .profileImage("modify" + TestConstants.PROFILEIMAGE)
                .build();
        Call<SignUpResponseDto> userModifyRequest = userService.patchUser(tokenHeader, userIdPathVariable, updateRequestDto);

        Response<SignUpResponseDto> userModifyResponse = userModifyRequest.execute();
        printResponseDetail(userModifyResponse);
    }

//    @Test
//    public void B_GroupServiceRequest_기능테스트() throws Throwable {
//        System.out.println(tokenHeader + ":" + userIdPathVariable);
//        GroupRequestDto groupRequestDto = GroupRequestDto.builder()
//                .active(TestConstants.ACTIVE)
//                .backgroundImage(TestConstants.BACKGROUNDIMAGE)
//                .category(TestConstants.CATEGORY)
//                .description(TestConstants.DESCRIPTION)
//                .memberLimit(TestConstants.MEMBERLIMIT)
//                .created(TestConstants.CREATED)
//                .name(TestConstants.NAME)
//                .profileImage(TestConstants.PROFILEIMAGE)
//                .build();
//    }
}
