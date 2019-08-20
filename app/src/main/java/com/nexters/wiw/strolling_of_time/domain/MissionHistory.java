package com.nexters.wiw.strolling_of_time.domain;

import java.time.LocalDateTime;

public class MissionHistory {
  private Mission mission;

  private User user;

  private int time;

  private LocalDateTime updated;

  public MissionHistory(User user, Mission mission, int time) {
    this.mission = mission;
    this.user = user;
    this.time = time;
  }
}