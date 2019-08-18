package com.nexters.wiw.strolling_of_time.domain;


import androidx.annotation.NonNull;

import com.nexters.wiw.strolling_of_time.models.EstimatedTime;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class PatternTimer implements Iterable<EstimatedTime>{
  private TimerTask task;
  private List<EstimatedTime> estimatedTimes;

  public PatternTimer(TimerTask task, List<EstimatedTime> estimatedTimes){
    this.task = task;
    this.estimatedTimes = estimatedTimes;
  }

  public void run(){
    // TODO: Rxjava Completable.timer
    // 1) 패턴 개수만큼 시작
    for (EstimatedTime time : this) {
      System.out.println(time.toString() + ": " + time.toSeconds());
      // 2) 타이머 동작
      this.task.run();
    }
  }

  @NonNull
  @Override
  public Iterator<EstimatedTime> iterator() {
    return new EstimatedIterator();
  }

  private class EstimatedIterator implements Iterator<EstimatedTime> {
    private int position = 0;

    public boolean hasNext() {
      return position < estimatedTimes.size();
    }

    public EstimatedTime next() {
      if (this.hasNext())
        return estimatedTimes.get(position++);
      else
        return null;
    }

    @Override
    public void remove() {
    }
  }
}