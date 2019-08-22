package com.nexters.wiw.strolling_of_time;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import com.nexters.wiw.strolling_of_time.domain.Auth;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.Group;
import com.nexters.wiw.strolling_of_time.domain.GroupNotice;
import com.nexters.wiw.strolling_of_time.domain.GroupNoticeService;
import com.nexters.wiw.strolling_of_time.domain.GroupService;
import com.nexters.wiw.strolling_of_time.dto.GroupNoticeRequestDto;
import com.nexters.wiw.strolling_of_time.dto.GroupRequestDto;

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
public class GroupNoticeServiceTest {
  private String TAG = getClass().getSimpleName();

  private AuthService authService;
  private GroupNoticeService groupNoticeService;
  private GroupService groupService;

  @Before
  public void setUp() {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(String.format("http://%s/api/v1/", BuildConfig.API_ADDRESS))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    authService = retrofit.create(AuthService.class);
    groupNoticeService = retrofit.create(GroupNoticeService.class);
    groupService = retrofit.create(GroupService.class);

  }

  @Test
  public void 그룹공지_생성_가져오기_삭제_테스트(){
    // 토큰 테스트
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> authCall = authService.login(basicAuth);
    Response<Auth> authResponse = null;
    try {
      authResponse = authCall.execute();

      authResponse.body().getToken();
    } catch (IOException e) {
      Log.e(TAG, "authRequestError : ", e);
      assert false;
    }
    assert authResponse.isSuccessful();

    // 그룹 생성 테스트
    GroupRequestDto groupRequestDto = new GroupRequestDto(
            TestConstants.ACTIVE,
            TestConstants.BACKGROUNDIMAGE,
            TestConstants.CATEGORY,
            TestConstants.DESCRIPTION,
            TestConstants.MEMBERLIMIT,
            TestConstants.CREATED,
            TestConstants.NAME,
            TestConstants.PROFILEIMAGE);

    Call<Group> createGroupCall = groupService.createGroup(
            "Bearer " + authResponse.body().getToken(),
            groupRequestDto
    );
    Response<Group> createGroupResponse = null;
    try {
      createGroupResponse = createGroupCall.execute();
      if(createGroupResponse.isSuccessful()) {
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body());
      }else{
        Log.d(TAG, "그룹_생성_테스트 실패 : " + createGroupResponse.errorBody());
      }
    } catch (IOException e) {
      Log.e(TAG, "그룹_생성_테스트: ", e);
      assert false;
    }

    // 그룹공지 생성 테스트
    GroupNoticeRequestDto groupNoticeRequestDto = new GroupNoticeRequestDto(
            TestConstants.CONTENT,
            TestConstants.CREATED,
            TestConstants.TITLE
    );
    Call<GroupNotice> createGroupNoticeCall = groupNoticeService.createGroupNotice(
            "Bearer " + authResponse.body().getToken(),
            groupNoticeRequestDto,
            createGroupResponse.body().getId()
    );
    Response<GroupNotice> createGroupNoticeResponse = null;
    try {
      createGroupNoticeResponse = createGroupNoticeCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "그룹공지_생성_수정_삭제_테스트: ", e);
      assert false;
    }
    Log.e(TAG, "그룹공지_생성_삭제_테스트: " + createGroupNoticeResponse.body().getTitle());

    // 그룹공지 가져오기 테스트
    Call<GroupNotice> getGroupNoticeCall = groupNoticeService.getGroupNotice(
            createGroupNoticeResponse.body().getId()
    );
    Response<GroupNotice> getGroupNoticeResponse = null;
    try {
      getGroupNoticeResponse = getGroupNoticeCall.execute();
      // 생성한 그룹공지 제목과 직접 집어넣은 그룹공지 제목이 같다면 통과
      Assert.assertEquals(TestConstants.TITLE, getGroupNoticeResponse.body().getTitle());
    } catch (IOException e) {
      Log.e(TAG, "그룹공지_생성_삭제_테스트: ", e);
      assert false;
    }

    // 그룹공지 삭제 테스트
    Call<Void> deleteGroupNoticeCall = groupNoticeService.deleteGroupNotice(
            createGroupNoticeResponse.body().getId()
    );
    Response<Void> deleteGroupNoticeResponse = null;
    try {
      deleteGroupNoticeResponse = deleteGroupNoticeCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "그룹공지_생성_수정_삭제_테스트: ", e);
      assert false;
    }
    // 정상적으로 그룹공지가 삭제 되었다면 통과
    assert deleteGroupNoticeResponse.isSuccessful();
  }

  @Test
  public void 그룹공지_생성_수정_삭제_테스트() {
    // 토큰 테스트
    String basicAuth = Auth.basicAuthHeaderOf(TestConstants.EMAIL, TestConstants.PASSWORD);
    Call<Auth> authCall = authService.login(basicAuth);
    Response<Auth> authResponse = null;
    try {
      authResponse = authCall.execute();
      authResponse.body().getToken();
    } catch (IOException e) {
      Log.e(TAG, "authRequestError : ", e);
      assert false;
    }
    assert authResponse.isSuccessful();

    // 그룹 생성 테스트
    GroupRequestDto groupRequestDto = new GroupRequestDto(
            TestConstants.ACTIVE,
            TestConstants.BACKGROUNDIMAGE,
            TestConstants.CATEGORY,
            TestConstants.DESCRIPTION,
            TestConstants.MEMBERLIMIT,
            TestConstants.CREATED,
            TestConstants.NAME,
            TestConstants.PROFILEIMAGE);

    Call<Group> createGroupCall = groupService.createGroup(
            "Bearer " + authResponse.body().getToken(),
            groupRequestDto
    );
    Response<Group> createGroupResponse = null;
    try {
      createGroupResponse = createGroupCall.execute();
      if(createGroupResponse.isSuccessful()) {
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body());
      }else{
        Log.d(TAG, "그룹_생성_테스트 실패 : " + createGroupResponse.errorBody());
      }
    } catch (IOException e) {
      Log.e(TAG, "그룹_생성_테스트: ", e);
      assert false;
    }

    // 그룹공지 생성 테스트
    GroupNoticeRequestDto groupNoticeRequestDto = new GroupNoticeRequestDto(
            TestConstants.CONTENT,
            TestConstants.CREATED,
            TestConstants.TITLE
    );
    Call<GroupNotice> createGroupNoticeCall = groupNoticeService.createGroupNotice(
            "Bearer " + authResponse.body().getToken(),
            groupNoticeRequestDto,
            createGroupResponse.body().getId()
    );
    Response<GroupNotice> createGroupNoticeResponse = null;
    try {
      createGroupNoticeResponse = createGroupNoticeCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "그룹공지_생성_수정_삭제_테스트: ", e);
      assert false;
    }
    Log.e(TAG, "그룹공지_생성_삭제_테스트: " + createGroupNoticeResponse.body().getTitle());

    // 그룹공지 수정 테스트
    groupNoticeRequestDto = new GroupNoticeRequestDto(
            "Update" + TestConstants.CONTENT,
            TestConstants.CREATED,
            "Update" + TestConstants.TITLE
    );
    Call<GroupNotice> updateGroupNoticeCall = groupNoticeService.updateGroupNotice(
            createGroupNoticeResponse.body().getId(),
            groupNoticeRequestDto
    );
    Response<GroupNotice> updateGroupNoticeResponse;
    try {
      updateGroupNoticeResponse = updateGroupNoticeCall.execute();
      if(updateGroupNoticeResponse.isSuccessful()){
        // 업데이트 한 그룹공지 제목과 직접 집어넣은 그룹공지 제목이 같다면 통과
        Assert.assertEquals("Update" + TestConstants.TITLE, updateGroupNoticeResponse.body().getTitle());
      }else{
        Log.d(TAG, "그룹공지_생성_수정_삭제_테스트: connection_Error");
      }
    } catch (IOException e) {
      Log.e(TAG, "그룹공지_생성_수정_삭제_테스트: ", e);
      assert false;
    }

    // 그룹공지 삭제 테스트
    Call<Void> deleteGroupNoticeCall = groupNoticeService.deleteGroupNotice(
            createGroupNoticeResponse.body().getId()
    );
    Response<Void> deleteGroupNoticeResponse = null;
    try {
      deleteGroupNoticeResponse = deleteGroupNoticeCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "그룹공지_생성_수정_삭제_테스트: ", e);
      assert false;
    }
    // 정상적으로 그룹공지가 삭제 되었다면 통과
    assert deleteGroupNoticeResponse.isSuccessful();
  }
}
