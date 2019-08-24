package com.nexters.wiw.strolling_of_time;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import com.nexters.wiw.strolling_of_time.domain.Auth;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.Group;
import com.nexters.wiw.strolling_of_time.domain.GroupService;
import com.nexters.wiw.strolling_of_time.dto.GroupRequestDto;
import com.nexters.wiw.strolling_of_time.dto.GroupResponseDto;

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
public class GroupServiceTest {
    private String TAG = getClass().getSimpleName();

    // TODO: Service mocking
    private AuthService authService;
    private GroupService groupService;

    @Before
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(AuthService.class);
        groupService = retrofit.create(GroupService.class);
    }

    // Todo 필독
    // 이 테스트를 수행하기에 앞서 User 계정이 있어야함
    // UserServiceRetrofitTest.java 의 @Test 주석을 제거한 후 실행한 뒤 수행할 것
    @Test
    public void 그룹_생성_가져오기_수정_삭제_테스트() {
        // 토큰 테스트
        String basicAuth = Auth.basicAuthHeaderOf(TestConstants.GROUP_TEST_EMAIL, TestConstants.GROUP_TEST_PASSWORD);
        Call<Auth> authCall = authService.login(basicAuth);
        Response<Auth> authRes = null;
        try {
            authRes = authCall.execute();
            assert authRes.isSuccessful();
        } catch (IOException e) {
            Log.e(TAG, "authRequestError : ", e);
            assert false;
        }
        Assert.assertNotNull(authRes);


        // 그룹 생성 테스트
        GroupRequestDto groupRequestDto = new GroupRequestDto(TestConstants.ACTIVE,
                TestConstants.BACKGROUNDIMAGE, TestConstants.CATEGORY, TestConstants.DESCRIPTION,
                TestConstants.MEMBERLIMIT, TestConstants.CREATED, TestConstants.NAME, TestConstants.PROFILEIMAGE);

        Call<Group> createGroupCall = groupService.createGroup(
                "Bearer " + authRes.body().getToken(),
                groupRequestDto
        );
        Response<Group> createGroupRes = null;
        try {
            createGroupRes = createGroupCall.execute();
            assert createGroupRes.isSuccessful();
            if (createGroupRes.isSuccessful()) {
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getId());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getName());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getBackgroundImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getCategory());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getCreated());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getDescription());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getProfileImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getMemberLimit());
            } else {
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트 실패 : " + createGroupRes.errorBody());
            }
        } catch (IOException e) {
            Log.e(TAG, "그룹_생성_가져오기_수정_삭제_테스트: ", e);
            assert false;
        }
        Assert.assertNotNull(createGroupRes);


        // 그룹 가져오기 테스트
        Call<Group> getGroupCall = groupService.getGroup(
                "Bearer " + authRes.body().getToken(),
                createGroupRes.body().getId()
        );
        Response<Group> getGroupRes = null;
        try {
            getGroupRes = getGroupCall.execute();
            assert getGroupRes.isSuccessful();
            if (getGroupRes.isSuccessful()) {
                Assert.assertEquals(getGroupRes.body().getId(), createGroupRes.body().getId());
                Assert.assertEquals(getGroupRes.body().getName(), createGroupRes.body().getName());
                Assert.assertEquals(getGroupRes.body().getBackgroundImage(), createGroupRes.body().getBackgroundImage());
                Assert.assertEquals(getGroupRes.body().getCategory(), createGroupRes.body().getCategory());
                Assert.assertEquals(getGroupRes.body().getDescription(), createGroupRes.body().getDescription());
                Assert.assertEquals(getGroupRes.body().getMemberLimit(), createGroupRes.body().getMemberLimit());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getId());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getName());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getBackgroundImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getCategory());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getDescription());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getProfileImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + createGroupRes.body().getMemberLimit());
            } else {
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트 실패 : " + getGroupRes.errorBody());
            }
        } catch (IOException e) {
            Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: ", e);
            assert false;
        }


        // 그룹들 가져오기 테스트
        Call<GroupResponseDto> getGroupsCall = groupService.getGroups(
                "Bearer " + authRes.body().getToken(),
                TestConstants.CATEGORY, TestConstants.NAME, 0, "new"
        );
        Response<GroupResponseDto> getGroupsRes = null;
        try {
            getGroupsRes = getGroupsCall.execute();
            assert getGroupsRes.isSuccessful();
            GroupResponseDto groupResponseDto = getGroupsRes.body();
            for (int i = 0; i < groupResponseDto.getContent().size(); i++) {
                GroupResponseDto.Content content = groupResponseDto.getContent().get(i);
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getBackgroundImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getCategory());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getCreated());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getDescription());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getName());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getProfileImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getActive());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getId());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + content.getMemberLimit());
            }
            Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + groupResponseDto.getNumber());
            Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + groupResponseDto.getSize());
            Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + groupResponseDto.getTotalElements());
            Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + groupResponseDto.getTotalPages());
        } catch (IOException e) {
            Log.e(TAG, "그룹_생성_가져오기_수정_삭제_테스트: ", e);
            Assert.fail();
        }
        Assert.assertNotNull(getGroupsRes);


        // 그룹 수정 테스트
        GroupRequestDto updateGroupDto = new GroupRequestDto(
                TestConstants.ACTIVE,
                "Update" + TestConstants.BACKGROUNDIMAGE,
                "Update" + TestConstants.CATEGORY,
                "Update" + TestConstants.DESCRIPTION,
                TestConstants.MEMBERLIMIT,
                TestConstants.CREATED,
                "Update" + TestConstants.NAME,
                "Update" + TestConstants.PROFILEIMAGE
        );
        Call<Group> updateGroupCall = groupService.patchGroup(
                "Bearer " + authRes.body().getToken(),
                updateGroupDto,
                createGroupRes.body().getId()
        );
        Response<Group> updateGroupResponse = null;
        try {
            updateGroupResponse = updateGroupCall.execute();
            assert updateGroupResponse.isSuccessful();
            if (updateGroupResponse.isSuccessful()) {
                Assert.assertEquals("Update" + TestConstants.NAME, updateGroupResponse.body().getName());
                Assert.assertEquals("Update" + TestConstants.BACKGROUNDIMAGE, updateGroupResponse.body().getBackgroundImage());
                Assert.assertEquals("Update" + TestConstants.CATEGORY, updateGroupResponse.body().getCategory());
                Assert.assertEquals("Update" + TestConstants.DESCRIPTION, updateGroupResponse.body().getDescription());
                Assert.assertEquals("Update" + TestConstants.PROFILEIMAGE, updateGroupResponse.body().getProfileImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + updateGroupResponse.body().getName());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + updateGroupResponse.body().getBackgroundImage());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + updateGroupResponse.body().getCategory());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + updateGroupResponse.body().getDescription());
                Log.d(TAG, "그룹_생성_가져오기_수정_삭제_테스트: " + updateGroupResponse.body().getProfileImage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(updateGroupResponse);


        //그룹 삭제 테스트
        Call<Void> deleteGroupCall = groupService.deleteGroup(
                "Bearer " + authRes.body().getToken(),
                createGroupRes.body().getId()
        );
        Response<Void> deleteGroupResponse = null;
        try {
            deleteGroupResponse = deleteGroupCall.execute();
        } catch (IOException e) {
            Log.e(TAG, "그룹_생성_가져오기_삭제_테스트: ", e);
            assert false;
        }

        // 성공적으로 삭제 했다면 통과
        assert deleteGroupResponse.isSuccessful();
    }
}