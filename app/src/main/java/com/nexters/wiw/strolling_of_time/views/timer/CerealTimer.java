package com.nexters.wiw.strolling_of_time.views.timer;

import android.graphics.Color;
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

import static com.nexters.wiw.strolling_of_time.R.drawable.cereal_timer_success;

public class CerealTimer extends AppCompatActivity {
    private boolean running = false;
    private int percentage = 0;

    private final Timer timer = new Timer();
    private ArcTimerView timerView;
    private TextView runningPercent;

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
        View cerealTimerBackground = findViewById(R.id.cereal_timer_background);
        View cerealTimerButton = findViewById(R.id.cereal_timer);
        Button pauseButton = findViewById(R.id.cereal_timer_pause);

        TextView estimateLabel = findViewById(R.id.cereal_timer_estimate_time_label);
        TextView estimateTime = findViewById(R.id.cereal_timer_estimate_time);

        TextView readyText = findViewById(R.id.tv_ready);


        runningPercent = findViewById(R.id.tv_running_percent);
        TextView totalRunningTime = findViewById(R.id.tv_total_running_time);
        TextView percentMark = findViewById(R.id.percent_mark);

        cerealTimerButton.setOnClickListener(v -> {
            running = !running;
            int visibility = running ? View.VISIBLE : View.GONE;
            int backgroundColor = running ? R.color.cereal_timer_running_color_background : R.color.cereal_timer_ready_color_background;
            int resBackgroundTextColor = running ? R.color.cereal_timer_ready_color_background : R.color.cereal_timer_running_color_background;
            int backgroundTextColor = getResources().getColor(resBackgroundTextColor);

            pauseButton.setVisibility(visibility);
            runningPercent.setVisibility(visibility);
            totalRunningTime.setVisibility(visibility);
            percentMark.setVisibility(visibility);

            readyText.setVisibility(running ? View.GONE : View.VISIBLE);

            cerealTimerBackground.setBackgroundResource(backgroundColor);
            estimateLabel.setTextColor(backgroundTextColor);
            estimateTime.setTextColor(backgroundTextColor);

            // TODO: 타이머 시간 흐르는 것으로 변경
            run(10);
//            runningPercent.setText(String.format("%d", percentage));
//            if(percentage >= 100) {
//                cerealTimerButton.setBackground(getResources().getDrawable(cereal_timer_success));
//                totalRunningTime.setTextColor(Color.WHITE);
//            }
//            if(running) {
//                percentage += 10;
//            }
        });
    }
    // 결과에 따라 값 처리하기
    private void run(final float concentrationTime){
        TimerTask task = new TimerTask() {
            float nowDegree = 0;
            @Override
            public void run() {
                final float MAX_DEGREE = 360; // Timer의 모양 360도

                // 총 걸리는 시간 설정
                nowDegree += (MAX_DEGREE / concentrationTime);

                // 최대 각도 넘어섰을 경우 타이머 종료
                if (nowDegree > MAX_DEGREE){
                    Log.d("", "한시간 지났다.");
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
        int interval = 1000;
        timer.schedule(task, 0, interval);
    }

    // Task에서는 UI를 변경할 수 없으므로 UI 제어를 위한 Handler 사용
    private final NonLeakHandler handler = new NonLeakHandler(this);

    private static final class NonLeakHandler extends Handler {
        private final WeakReference<CerealTimer> ref;

        NonLeakHandler(CerealTimer act) {
            ref = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            CerealTimer act = ref.get();
            if (act != null) {
                act.initCerealTimer();
            }
        }
    }
}
