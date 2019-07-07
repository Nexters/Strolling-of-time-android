package com.nexters.wiw.strolling_of_time;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.assertEquals;

public class CompletableTimerTest {
  @Test
  public void timer() {

    // 테스트용 스케쥴러; 시간을 마음대로 이동시킬 수 있다
    final TestScheduler testScheduler = new TestScheduler();

    final AtomicLong atomicLong = new AtomicLong();
    // delay 시간 후 동작
    Disposable disposable = Completable.timer(2, TimeUnit.SECONDS, testScheduler)
            .subscribe(() -> System.out.println(atomicLong.incrementAndGet()));

    assertEquals(0, atomicLong.get());

    // 1초 뒤로 이동
    testScheduler.advanceTimeBy(1, TimeUnit.SECONDS);

    // 값 가져오기 확인
    assertEquals(0, atomicLong.get());

    // 1초 뒤로 이동
    testScheduler.advanceTimeBy(100, TimeUnit.SECONDS);

    // 값 가져오기 확인 (2초 지남)
    assertEquals(1, atomicLong.get());

    disposable.dispose();
  }
}
