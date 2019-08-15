package com.nexters.wiw.strolling_of_time;

import android.support.annotation.NonNull;

import com.nexters.wiw.strolling_of_time.domain.PatternTimer;
import com.nexters.wiw.strolling_of_time.models.EstimatedTime;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

  class TimerMock extends Timer{
    private TimerTask task;
    private long delay;

    @Override
    public void schedule(TimerTask task, long delay) {
      // super.schedule(task, delay); // mocking
      System.out.println("schedule TEST!");
      this.task = task;
      this.delay = delay;
    }
    public boolean isCalled(){
      return task != null & delay != 0;
    }
  }

  class TimerTaskMock extends TimerTask{
    @Override
    public void run() {
      System.out.println("Task TEST!");
    }
  }

  @Test
  public void test_pattern_timer(){
    ArrayList<EstimatedTime> estimatedTimes = new ArrayList<>();

    // EstimatedTime initialize test
    EstimatedTime oneSeconds = new EstimatedTime(1, TimeUnit.SECONDS);
    EstimatedTime twoMinutes = new EstimatedTime(2, TimeUnit.MINUTES);
    EstimatedTime threeHours = new EstimatedTime(3, TimeUnit.HOURS);

    assert(oneSeconds.toSeconds() == 1);
    assert(twoMinutes.toSeconds() == 2 * 60);
    assert(threeHours.toSeconds() == 3 * 3600);

    estimatedTimes.add(oneSeconds);
    estimatedTimes.add(twoMinutes);
    estimatedTimes.add(threeHours);

    // PatternTimer TEST
    TimerTask task = new TimerTaskMock();
    PatternTimer patternTimer = new PatternTimer(task, estimatedTimes);
    patternTimer.run(); // 타이머 시작

    Disposable disposable = Observable.just("Hello", "RxJava2")
            .subscribe(System.out::println);
    disposable.dispose();
  }
}