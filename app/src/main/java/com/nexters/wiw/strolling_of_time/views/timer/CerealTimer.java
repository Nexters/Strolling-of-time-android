package com.nexters.wiw.strolling_of_time.views.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nexters.wiw.strolling_of_time.R;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.round;

public class CerealTimer extends AppCompatActivity {
  private boolean running = false;
  private long estimateTimeSeconds = 3;
  private int runningCount = 0;
  private long runningTime = 0; // milliseconds
  private int percentage = 0;

  private ArcTimerView timerView;
  private float nowDegree = 0;
  private TextView runningPercent;
  private View cerealTimerStartButton;

  // Task에서는 UI를 변경할 수 없으므로 UI 제어를 위한 Handler 사용
  private final NonLeakHandler handler = new NonLeakHandler(this);
  private final Timer timer = new Timer();
  private TimerTask task;

  private TextView estimateTime;
  private TextView totalRunningTime;
  private String estimateTimeFormat = "%02d:%02d:%02d";

  private static final class NonLeakHandler extends Handler {
    private final WeakReference<CerealTimer> ref;

    NonLeakHandler(CerealTimer act) {
      ref = new WeakReference<>(act);
    }

    @Override
    public void handleMessage(Message msg) {
      CerealTimer act = ref.get();
      if(act != null) {
        Bundle bundle = msg.getData();
        String status = bundle.getString("status");
        if(status == null) {
          Log.d(getClass().getSimpleName(), "handleMessage: status null");
          return;
        }

        if(status.equals("done")) {
          act.timerView.initialize();
        } else if (status.equals("doing")){
          long seconds = act.runningTime / 1000;
          long minutes = seconds / 60;
          long hours = minutes / 60;

          String time = String.format(
                  act.estimateTimeFormat, hours % 24, minutes % 60, seconds % 60);
          act.totalRunningTime.setText(time);

          @SuppressLint("DefaultLocale")
          String percentage_ = String.format("%d", act.percentage);

          act.runningPercent.setText(percentage_);
        }
      }
    }
  }

  private TimerTask newTimerTask(final float estimateSecond) {
    return new TimerTask() {
      @Override
      public void run() { // millisecond 단위로 움직임
        final float MAX_DEGREE = 360;
        runningTime++;
        // 총 걸리는 시간 설정
        nowDegree += (MAX_DEGREE / estimateSecond);
        Bundle data = new Bundle();
        percentage = (int)((runningTime / estimateSecond) * 100);

        if(nowDegree > MAX_DEGREE) {
          Log.d("", "시간 완료: " + runningCount++);
          nowDegree = 0;
          data.putString("status", "done");
          Message msg = handler.obtainMessage();
          msg.setData(data);
          handler.sendMessage(msg);
        } else {
          timerView.setTime(nowDegree);
          data.putString("status", "doing");
          Message msg = handler.obtainMessage();
          msg.setData(data);
          handler.sendMessage(msg);
        }
        timerView.invalidate();
      }
    };
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

    cerealTimerStartButton = findViewById(R.id.cereal_timer);
    Button pauseButton = findViewById(R.id.cereal_timer_pause);

    TextView estimateLabel = findViewById(R.id.cereal_timer_estimate_time_label);

    estimateTime = findViewById(R.id.cereal_timer_estimate_time);

    TextView readyText = findViewById(R.id.tv_ready);


    runningPercent = findViewById(R.id.tv_running_percent);

    totalRunningTime = findViewById(R.id.tv_total_running_time);
    TextView percentMark = findViewById(R.id.percent_mark);

    cerealTimerStartButton.setOnClickListener(v -> {
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

      run(estimateTimeSeconds);
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
  private void run(final float second) {
    if(task == null) {
      task = newTimerTask(second*1000);
      timer.schedule(task, 0, 1);
    }
  }
}
