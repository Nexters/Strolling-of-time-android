package com.nexters.wiw.strolling_of_time.dto;

import androidx.annotation.NonNull;

public class MissionHistoryRequestDto {
  private int time;

  public MissionHistoryRequestDto(
          @NonNull int time){
    this.time = time;
  }
}
