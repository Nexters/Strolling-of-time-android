package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.UserRequestDto;
import com.nexters.wiw.strolling_of_time.dto.UserResponseDto;

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
  Call<UserResponseDto> getUsers(
          @Header("Authorization") String jwt,
          @Query("query") String query
  );

  @Headers("Content-Type: application/json")
  @POST("users")
  Call<User> signUp(@Body UserRequestDto user);

  @GET("users/{id}")
  Call<User> getUserById(
          @Header("Authorization") String jwt,
          @Path("id") Long id);

  @DELETE("users/{id}")
  Call<Void> deleteUserById(
          @Header("Authorization") String jwt,
          @Path("id") Long id);

  @Headers("Content-Type: application/json")
  @PATCH("users/{id}")
  Call<User> patchUser(
          @Header("Authorization") String jwt,
          @Path("id") Long id,
          @Body UserRequestDto user);
}
