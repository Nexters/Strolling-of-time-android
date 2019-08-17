package com.nexters.wiw.strolling_of_time.views.timer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.R;

import static com.nexters.wiw.strolling_of_time.R.drawable.cereal_timer_success;

public class CerealTimer extends AppCompatActivity {
    private boolean running = false;
    private int percentage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cereal_timer);
        initBottomSheet();
        initCerealTimer();
    }

    private void initBottomSheet(){
        Button bottomSheet = findViewById(R.id.btn_bottom_sheet);
        bottomSheet.setOnClickListener(v->{
            CoworkerBottomDialogFragment coworkerBottomDialogFragment =
                    CoworkerBottomDialogFragment.newInstance();
            coworkerBottomDialogFragment.show(getSupportFragmentManager(),
                    "coworker_bottom_dialog_fragment");
            coworkerBottomDialogFragment.setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.SheetDialog);
        });
    }

    private void initCerealTimer(){
        View cerealTimerBackground = findViewById(R.id.cereal_timer_background);
        View cerealTimerButton = findViewById(R.id.cereal_timer);
        Button pauseButton = findViewById(R.id.cereal_timer_pause);

        TextView estimateLabel = findViewById(R.id.cereal_timer_estimate_time_label);
        TextView estimateTime = findViewById(R.id.cereal_timer_estimate_time);

        TextView readyText = findViewById(R.id.tv_ready);

        TextView runningPercent = findViewById(R.id.tv_running_percent);
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
            runningPercent.setText(String.format("%d", percentage));
            if(percentage >= 100) {
                cerealTimerButton.setBackground(getResources().getDrawable(cereal_timer_success));
                totalRunningTime.setTextColor(Color.WHITE);
            }
            if(running) {
                percentage += 10;
            }
        });
    }
}
