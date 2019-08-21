package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.MissionHistoryRequestDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MissionHistoryService {
  @GET("mission/{id}/times")
  Call<MissionHistory> getMissionTime(
          @Header("Authorization") String auth,
          @Path("id") Long id
  );

  @Headers("Content-Type: application/json")
  @POST("mission/{id}/times")
  Call<MissionHistory> createMissionTime(
          @Path("id") Long id,
          @Body MissionHistoryRequestDto mission
  );
}
