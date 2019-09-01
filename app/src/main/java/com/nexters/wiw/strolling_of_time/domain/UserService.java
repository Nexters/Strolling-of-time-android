package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.UserModifyRequestDto;
import com.nexters.wiw.strolling_of_time.dto.UserSearchResponseDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpRequestDto;
import com.nexters.wiw.strolling_of_time.dto.SignUpResponseDto;

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

public interface UserService {
  @GET("users")
  Call<UserSearchResponseDto> getUsers(
          @Header("Authorization") String jwt,
          @Query("email") String email,
          @Query("nickname") String nickName
  );

  @Headers("Content-Type: application/json")
  @POST("users")
  Call<SignUpResponseDto> signUp(@Body SignUpRequestDto user);

  @GET("users/{id}")
  Call<SignUpResponseDto> getUserById(
          @Header("Authorization") String jwt,
          @Path("id") long id);

  @DELETE("users/{id}")
  Call<Void> deleteUserById(
          @Header("Authorization") String jwt,
          @Path("id") long id);

  @Headers("Content-Type: application/json")
  @PATCH("users/{id}")
  Call<SignUpResponseDto> patchUser(
          @Header("Authorization") String jwt,
          @Path("id") long id,
          @Body UserModifyRequestDto user);
}
