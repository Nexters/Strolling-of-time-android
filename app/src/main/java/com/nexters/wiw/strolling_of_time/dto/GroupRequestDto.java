package com.nexters.wiw.strolling_of_time.dto;

import androidx.annotation.Nullable;
import androidx.annotation.Size;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
