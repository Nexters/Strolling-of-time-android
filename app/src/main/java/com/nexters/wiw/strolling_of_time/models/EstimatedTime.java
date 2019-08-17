package com.nexters.wiw.strolling_of_time.models;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

public class EstimatedTime{
  private int time; // TimeUnit.SECONDS MINUTES HOURS
  private TimeUnit unit; // 단위

  public EstimatedTime(int time, TimeUnit unit){
    this.time = time;
    this.unit = unit;
  }

  @NonNull
  @Override
  public String toString() {
    return this.time + " " + this.unit.name();
  }
  public long toSeconds(){
    return unit.toSeconds(this.time);
  }
}