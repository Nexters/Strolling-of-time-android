package com.nexters.wiw.strolling_of_time.domain;

import java.time.LocalDateTime;

public class Mission {
  private long id;

  private Group group;

  private String name;

  private String description;

  private int expectLearningTime;

  private LocalDateTime estimate;

  private LocalDateTime updated;

  private LocalDateTime created;

  public Mission(String name, String description, int expectLearningTime, LocalDateTime estimate) {
    this.name = name;
    this.description = description;
    this.expectLearningTime = expectLearningTime;
    this.estimate = estimate;
  }
}
