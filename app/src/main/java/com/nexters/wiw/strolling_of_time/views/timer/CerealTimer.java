package com.nexters.wiw.strolling_of_time.views.timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nexters.wiw.strolling_of_time.R;



public class CerealTimer extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cereal_timer);
    initBottomSheet();
    initCerealTimer();
  }

  private void initBottomSheet(){
    // TODO: ViewModel or RxAndroid를 이용한 같이 참여중인 사람 보여주기

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
    ToggleButton cerealTimerButton = findViewById(R.id.cereal_timer);
    Button pauseButton = findViewById(R.id.cereal_timer_pause);
    TextView estimateLabel = findViewById(R.id.cereal_timer_estimate_time_label);
    TextView estimateTime = findViewById(R.id.cereal_timer_estimate_time);

    cerealTimerButton.setOnCheckedChangeListener((compoundButton, running) -> {
      int visibility = running ? View.VISIBLE : View.GONE;
      int backgroundColor = running ? R.color.cereal_timer_running_color_background : R.color.cereal_timer_ready_color_background;
      int resBackgroundTextColor = running ? R.color.cereal_timer_ready_color_background : R.color.cereal_timer_running_color_background;
      int backgroundTextColor = getResources().getColor(resBackgroundTextColor);

      pauseButton.setVisibility(visibility);
      cerealTimerBackground.setBackgroundResource(backgroundColor);
      estimateLabel.setTextColor(backgroundTextColor);
      estimateTime.setTextColor(backgroundTextColor);
    });
  }
}
