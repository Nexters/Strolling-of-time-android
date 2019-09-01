package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.Group;
import com.nexters.wiw.strolling_of_time.dto.GroupRequestDto;
import com.nexters.wiw.strolling_of_time.dto.GroupResponseDto;

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

public interface GroupService {
  @GET("groups")
  Call<GroupResponseDto> getGroups(
          @Header("Authorization") String auth,
          @Query("category") String category,
          @Query("name") String name,
          @Query("page") int page,
          @Query("sort") String sort
  );

  @GET("groups/{id}")
  Call<Group> getGroup(
          @Header("Authorization") String auth,
          @Path("id") Long id
  );

  @Headers("Content-Type: application/json")
  @POST("groups")
  Call<Group> createGroup(
          @Header("Authorization") String auth,
          @Body GroupRequestDto group
  );

  @DELETE("groups/{id}")
  Call<Void> deleteGroup(
          @Header("Authorization") String auth,
          @Path("id") Long id
  );

  @PATCH("groups/{id}")
  Call<Group> patchGroup(
          @Header("Authorization") String auth,
          @Body GroupRequestDto group,
          @Path("id") Long id
  );

}