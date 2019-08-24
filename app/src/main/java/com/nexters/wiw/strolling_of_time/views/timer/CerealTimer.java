package com.nexters.wiw.strolling_of_time.views.timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nexters.wiw.strolling_of_time.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class CerealTimer extends AppCompatActivity {
  private boolean running = false;
  private int percentage = 0;

  private ArcTimerView timerView;
  private float nowDegree = 0;
  private TextView runningPercent;
  private View cerealTimerButton;

  // Task에서는 UI를 변경할 수 없으므로 UI 제어를 위한 Handler 사용
  private final NonLeakHandler handler = new NonLeakHandler(this);
  private final Timer timer = new Timer();
  private TimerTask task;

  private static final class NonLeakHandler extends Handler {
    private final WeakReference<CerealTimer> ref;

    NonLeakHandler(CerealTimer act) {
      ref = new WeakReference<>(act);
    }

    @Override
    public void handleMessage(Message msg) {
      CerealTimer act = ref.get();
      if(act != null) {
        act.initCerealTimer();
      }
    }
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cereal_timer);
    initBottomSheet();
    initCerealTimer();
  }

  private void initBottomSheet() {
    Button bottomSheet = findViewById(R.id.btn_bottom_sheet);
    bottomSheet.setOnClickListener(v -> {
      CoworkerBottomDialogFragment coworkerBottomDialogFragment =
              CoworkerBottomDialogFragment.newInstance();
      coworkerBottomDialogFragment.show(getSupportFragmentManager(),
              "coworker_bottom_dialog_fragment");
      coworkerBottomDialogFragment.setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog);
    });
  }

  private void initCerealTimer() {
    timerView = findViewById(R.id.timerView);
    timerView.initialize();
    View cerealTimerBackground = findViewById(R.id.cereal_timer_background);

    cerealTimerButton = findViewById(R.id.cereal_timer);
    Button pauseButton = findViewById(R.id.cereal_timer_pause);

    TextView estimateLabel = findViewById(R.id.cereal_timer_estimate_time_label);
    TextView estimateTime = findViewById(R.id.cereal_timer_estimate_time);

    TextView readyText = findViewById(R.id.tv_ready);


    runningPercent = findViewById(R.id.tv_running_percent);
    TextView totalRunningTime = findViewById(R.id.tv_total_running_time);
    TextView percentMark = findViewById(R.id.percent_mark);

    cerealTimerButton.setOnClickListener(v -> {
      running = true;

      int visibility = View.VISIBLE;
      int backgroundColor = R.color.cereal_timer_running_color_background;
      int resBackgroundTextColor = R.color.cereal_timer_ready_color_background;
      int backgroundTextColor = getResources().getColor(resBackgroundTextColor);

      pauseButton.setVisibility(visibility);
      runningPercent.setVisibility(visibility);
      totalRunningTime.setVisibility(visibility);
      percentMark.setVisibility(visibility);

      readyText.setVisibility(View.GONE);

      cerealTimerBackground.setBackgroundResource(backgroundColor);
      timerView.setMarginColor(backgroundColor);
      estimateLabel.setTextColor(backgroundTextColor);
      estimateTime.setTextColor(backgroundTextColor);

      run(10);
    });
    pauseButton.setOnClickListener(v -> {
      running = false;
      task.cancel();
      task = null;

      int visibility = View.GONE;
      int backgroundColor = R.color.cereal_timer_ready_color_background;
      int resBackgroundTextColor = R.color.cereal_timer_running_color_background;
      int backgroundTextColor = getResources().getColor(resBackgroundTextColor);

      pauseButton.setVisibility(visibility);
      runningPercent.setVisibility(visibility);
      totalRunningTime.setVisibility(visibility);
      percentMark.setVisibility(visibility);

      readyText.setVisibility(View.VISIBLE);

      cerealTimerBackground.setBackgroundResource(backgroundColor);
      timerView.setMarginColor(backgroundColor);
      estimateLabel.setTextColor(backgroundTextColor);
      estimateTime.setTextColor(backgroundTextColor);
    });
  }

  // 결과에 따라 값 처리하기
  private void run(final float concentrationTime) {
    if(task == null) {
      int interval = 1000;
      task = initTimerTask(concentrationTime*interval);
      timer.schedule(task, 0, interval/1000);
    }
  }

  private TimerTask initTimerTask(final float concentrationTime) {
    return new TimerTask() {
      @Override
      public void run() {
        final float MAX_DEGREE = 360; // Timer의 모양 360도

        // 총 걸리는 시간 설정
        nowDegree += (MAX_DEGREE / concentrationTime);

        // 최대 각도 넘어섰을 경우 타이머 종료
        if(nowDegree > MAX_DEGREE) {
          Log.d("", "시간 완료");
          handler.sendMessage(handler.obtainMessage());
          nowDegree = 0;
          running = false;
          cancel();
        } else {
          Log.d("", "handleMessage: " + nowDegree);
          timerView.setTime(nowDegree);
        }
        timerView.invalidate();
      }
    };
  }
}
