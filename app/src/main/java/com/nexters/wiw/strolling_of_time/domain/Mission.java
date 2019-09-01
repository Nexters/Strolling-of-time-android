package com.nexters.wiw.strolling_of_time.domain;

import com.nexters.wiw.strolling_of_time.dto.Group;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Mission {
  private long id;

  private Group group;

  private String name;

  private String description;

  private int expectLearningTime;

  private LocalDateTime estimate;

  private LocalDateTime updated;

  private LocalDateTime created;

  @Builder
  public Mission(String name, String description, int expectLearningTime, LocalDateTime estimate) {
    this.name = name;
    this.description = description;
    this.expectLearningTime = expectLearningTime;
    this.estimate = estimate;
  }
}
