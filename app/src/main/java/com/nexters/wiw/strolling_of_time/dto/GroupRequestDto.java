package com.nexters.wiw.strolling_of_time.dto;

import androidx.annotation.Size;

import lombok.Builder;

@Builder
public class GroupRequestDto {

  private boolean active = true;

  private String backgroundImage;

  private String category;

  @Size(min = 1, max = 100)
  private String description;

  private long memberLimit = 6;

  private String created;

  @Size(min = 1, max = 45)
  private String name;

  private String profileImage;
}
