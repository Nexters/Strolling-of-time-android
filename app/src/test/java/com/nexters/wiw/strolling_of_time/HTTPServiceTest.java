package com.nexters.wiw.strolling_of_time;

import com.nexters.wiw.strolling_of_time.domain.GroupService;
import com.nexters.wiw.strolling_of_time.dto.GroupPageResponseDto;
import com.nexters.wiw.strolling_of_time.dto.GroupRequestDto;
import com.nexters.wiw.strolling_of_time.dto.GroupResponseDto;
import com.nexters.wiw.strolling_of_time.dto.LoginRequestDto;
import com.nexters.wiw.strolling_of_time.dto.LoginResponseDto;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.UserService;
import com.nexters.wiw.strolling_of_time.dto.UserModifyRequestDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpRequestDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpResponseDto;

import org.junit.AfterClass;
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
    public void UserServiceRequest_기능테스트() throws Throwable {
        UserModifyRequestDto updateRequestDto = UserModifyRequestDto.builder()
                .nickname("modify" + TestConstants.NICKNAME)
                .password("modify" + TestConstants.PASSWORD)
                .profileImage("modify" + TestConstants.PROFILEIMAGE)
                .build();
        Call<SignUpResponseDto> userModifyRequest = userService.patchUser(tokenHeader, userIdPathVariable, updateRequestDto);

        Response<SignUpResponseDto> userModifyResponse = userModifyRequest.execute();
        printResponseDetail(userModifyResponse);
    }

    @Test
    public void GroupServiceRequest_기능테스트() throws Throwable {
        GroupRequestDto groupRequestDto = GroupRequestDto.builder()
                .active(TestConstants.ACTIVE)
                .backgroundImage(TestConstants.BACKGROUNDIMAGE)
                .category(TestConstants.CATEGORY)
                .description(TestConstants.DESCRIPTION)
                .memberLimit(TestConstants.MEMBERLIMIT)
                .created(TestConstants.CREATED)
                .name(TestConstants.NAME)
                .profileImage(TestConstants.PROFILEIMAGE)
                .build();
        Call<GroupResponseDto> groupCreateRequest = groupService.createGroup(tokenHeader, groupRequestDto);
        Response<GroupResponseDto> groupCreateResponse = groupCreateRequest.execute();
        printResponseDetail(groupCreateResponse);

        long groupId = Optional.of(groupCreateResponse)
                .filter(Response::isSuccessful)
                .map(Response::body)
                .map(GroupResponseDto::getId)
                .orElseThrow(Exception::new);

        Call<GroupResponseDto> getGroupRequest = groupService.getGroup(tokenHeader, groupId);
        Response<GroupResponseDto> getGroupResponse = getGroupRequest.execute();
        printResponseDetail(getGroupResponse);

        Call<GroupPageResponseDto> getGroupsRequest = groupService.getGroups(tokenHeader, null, null, 0, null);
        Response<GroupPageResponseDto> getGroupsResponse = getGroupsRequest.execute();
        printResponseDetail(getGroupsResponse);

        GroupRequestDto groupModifyDto = GroupRequestDto.builder()
                .active(TestConstants.ACTIVE)
                .backgroundImage("Modify"+TestConstants.BACKGROUNDIMAGE)
                .category("Modify"+TestConstants.CATEGORY)
                .description("Modify"+TestConstants.DESCRIPTION)
                .memberLimit(TestConstants.MEMBERLIMIT+1)
                .created(TestConstants.CREATED)
                .name("Modify"+TestConstants.NAME)
                .profileImage("Modify"+TestConstants.PROFILEIMAGE)
                .build();
        Call<GroupResponseDto> groupPatchRequest = groupService.patchGroup(tokenHeader, groupModifyDto, groupId);
        Response<GroupResponseDto> groupModifyResponse = groupPatchRequest.execute();
        printResponseDetail(groupModifyResponse);

        Call<Void> groupDeleteRequest = groupService.deleteGroup(tokenHeader, groupId);
        Response<Void> groupDeleteResponse = groupDeleteRequest.execute();
        printResponseDetail(groupDeleteResponse);
    }
}
