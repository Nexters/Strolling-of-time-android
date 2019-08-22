package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.GroupNoticeRequestDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupNoticeService {
  @GET("user/{id}/notices")
  Call<List<GroupNotice>> getGroupNoticeByUserId(
          @Path("id") Long id
  );

  @GET("group/{id}/notices")
  Call<List<GroupNotice>> getGroupNoticeByGroupId(
          @Path("id") Long id,
          @Query("keyword") String keyWord
  );

  @Headers("Content-Type: application/json")
  @POST("group/{id}/group-notice")
  Call<GroupNotice> createGroupNotice(
          @Header("Authorization") String jwt,
          @Body GroupNoticeRequestDto notice,
          @Path("id") Long id
  );

  @PATCH("group-notice/{id}")
  Call<GroupNotice> updateGroupNotice(
          @Path("id") Long id,
          @Body GroupNoticeRequestDto notice
  );

  @DELETE("group-notice/{id}")
  Call<Void> deleteGroupNotice(
          @Path("id") Long id
  );

  @GET("group-notice/{id}")
  Call<GroupNotice> getGroupNotice(
          @Path("id") Long id
  );
}
