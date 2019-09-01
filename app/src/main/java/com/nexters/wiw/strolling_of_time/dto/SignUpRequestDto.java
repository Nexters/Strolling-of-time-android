package com.nexters.wiw.strolling_of_time.dto;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import lombok.Builder;

public class SignUpRequestDto {

  private String nickname;

  private String email;

  private String password;

  private String profileImage;

  @Builder
  public SignUpRequestDto(
          @NonNull String email, @Size(min = 8, max = 15) String password,
          @NonNull String nickname, String profileImage){
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.profileImage = profileImage;
  }
}
