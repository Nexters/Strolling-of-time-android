package com.nexters.wiw.strolling_of_time;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import com.nexters.wiw.strolling_of_time.domain.Auth;
import com.nexters.wiw.strolling_of_time.domain.AuthService;
import com.nexters.wiw.strolling_of_time.domain.Group;
import com.nexters.wiw.strolling_of_time.domain.GroupService;
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

  // Todo 그룹하나를 생성하려면 @Test 주석 해제 후 테스트 실행
  @Test
  public void 그룹_생성_테스트(){
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
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getId());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getName());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getBackgroundImage());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getCategory());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getCreated());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getDescription());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getProfileImage());
        Log.d(TAG, "그룹_생성_테스트: "+ createGroupResponse.body().getMemberLimit());
      }else{
        Log.d(TAG, "그룹_생성_테스트 실패 : " + createGroupResponse.errorBody());
      }
    } catch (IOException e) {
      Log.e(TAG, "그룹_생성_테스트: ", e);
      assert false;
    }
  }

  @Test
  public void 그룹_생성_수정_삭제_테스트() {
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
        Log.d(TAG, "그룹_생성_수정_삭제_테스트: "+ createGroupResponse.body());
      }else{
        Log.d(TAG, "그룹_생성_수정_삭제_테스트 실패 : " + createGroupResponse.errorBody());
      }
    } catch (IOException e) {
      Log.e(TAG, "그룹_생성_수정_삭제_테스트: ", e);
      assert false;
    }

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
            "Bearer " + authResponse.body().getToken(),
            updateGroupDto,
            createGroupResponse.body().getId()
    );
    Response<Group> updateGroupResponse = null;
    try {
      updateGroupResponse = updateGroupCall.execute();
      if (updateGroupResponse.isSuccessful()){
        // 업데이트 한 그룹 이름과 직접 집어넣은 그룹이름이 같다면 통과
        Assert.assertEquals("Update"+TestConstants.NAME, updateGroupResponse.body().getName());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert updateGroupResponse.isSuccessful();

    // 업데이트 된 그룹 삭제 테스트
    Call<Void> deleteGroupCall = groupService.deleteGroup(
            "Bearer " + authResponse.body().getToken(),
            createGroupResponse.body().getId()
    );
    Response<Void> deleteGroupResponse = null;
    try {
      deleteGroupResponse = deleteGroupCall.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // 성공적으로 삭제 했다면 통과
    assert deleteGroupResponse.isSuccessful();
  }

  @Test
  public void 그룹_생성_삭제_테스트(){
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

    // 그룹을 생서했다면 아이디를 받아옴
    Log.e(TAG, "그룹_생성_삭제_테스트: " + createGroupResponse.body().getId());

    // 그룹 삭제 테스트
    Call<Void> deleteGroupCall = groupService.deleteGroup(
            "Bearer " + authResponse.body().getToken(),
            createGroupResponse.body().getId()
    );
    Response<Void> deleteGroupResponse = null;
    try {
      deleteGroupResponse = deleteGroupCall.execute();
    } catch (IOException e) {
      Log.e(TAG, "그룹_생성_삭제_테스트: ", e);
      assert false;
    }

    // 성공적으로 삭제 했다면 통과
    assert deleteGroupResponse.isSuccessful();
  }

  @Test
  public void 그룹_생성_가져오기_삭제_테스트(){
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

    // 그룹 가져오기 테스트
    Call<Group> getGroupCall = groupService.getGroups(
            "Bearer " + authResponse.body().getToken(),
            createGroupResponse.body().getId()
    );
    Response<Group> getGroupResponse = null;
    try {
      getGroupResponse = getGroupCall.execute();
      if(getGroupResponse.isSuccessful()){
        Log.d(TAG, "그룹_가져오기: " + getGroupResponse.body().getName());
      }else{
        Log.d(TAG, "그룹_가져오기_실패 : " + getGroupResponse.errorBody());
      }
    } catch (IOException e) {
      Log.d(TAG, "그룹_가져오기: ", e);
      assert false;
    }

    // 생성한 그룹 아이디와, 가져온 그룹아이디가 같다면 테스트 통과
    Assert.assertEquals(getGroupResponse.body().getId(), createGroupResponse.body().getId());

    // 그룹 삭제 테스트
    Call<Void> deleteGroupCall = groupService.deleteGroup(
            "Bearer " + authResponse.body().getToken(),
            createGroupResponse.body().getId()
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

