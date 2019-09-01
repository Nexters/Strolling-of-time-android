package com.nexters.wiw.strolling_of_time.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
  private String token;
  private String tokenType;
  private long expiresIn;
}
