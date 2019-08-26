package com.nexters.wiw.strolling_of_time.dto;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import java.time.LocalDateTime;

public class MissionRequestDto {
  private String name;
  private String description;
  private int expectLearningTime;
  private LocalDateTime estimate;

  public MissionRequestDto(
          @NonNull String name, @Size(max = 45) String description,
          @NonNull int expectLearningTime, @NonNull LocalDateTime estimate) {
    this.name = name;
    this.description = description;
    this.expectLearningTime = expectLearningTime;
    this.estimate = estimate;
  }
}
