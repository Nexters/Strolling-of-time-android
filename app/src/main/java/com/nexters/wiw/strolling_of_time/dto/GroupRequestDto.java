package com.nexters.wiw.strolling_of_time.dto;

import androidx.annotation.Size;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupRequestDto {
  @Size(min = 1, max = 45)
  private String name;

  @Size(min = 1, max = 100)
  private String description;

  private String profileImage;

  private String backgroundImage;

  private LocalDateTime created;

  private int memberLimit = 6;

  private boolean active = true;
}
