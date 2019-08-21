package com.nexters.wiw.strolling_of_time.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MissionHistory {
  private Mission mission;

  private User user;

  private int time;
  private LocalDateTime updated;


  public void addMission(Mission mission) {
    this.mission = mission;
  }

  public void addUser(User user) {
    this.user = user;
  }

  @Builder
  public MissionHistory(User user, Mission mission, int time) {
    this.mission = mission;
    this.user = user;
    this.time = time;
  }

  public MissionHistory update(MissionHistory missionHistory) {
    this.time = missionHistory.time;

    return this;
  }
}