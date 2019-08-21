package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.MissionRequestDto;

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

public interface MissionService {
  @GET("missions")
  Call<List<Mission>> getMissions();

  @Headers("Content-Type: application/json")
  @PATCH("mission/{id}")
  Call<Mission> patchMission(
          @Header("Authorization") String jwt,
          @Body MissionRequestDto mission,
          @Path("id") Long id
  );

  @DELETE("mission/{id}")
  Call<Void> patchMission(
          @Header("Authorization") String jwt,
          @Path("id") Long id
  );

  @GET("group/{id}/missions")
  Call<List<Mission>> getGroupMissions(
          @Header("Authorization") String jwt,
          @Path("id") Long groupId,
          @Query("end") int end
  );

  @Headers("Content-Type: application/json")
  @POST("group/{id}/mission")
  Call<Void> createMission(
          @Header("Authorization") String jwt,
          @Body MissionRequestDto mission,
          @Query("id") Long groupId
  );
}
