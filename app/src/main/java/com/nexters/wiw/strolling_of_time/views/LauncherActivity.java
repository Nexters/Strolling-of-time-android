package com.nexters.wiw.strolling_of_time.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.main.MainActivity;

import java.util.Random;

@SuppressLint("Registered")
public class LauncherActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);


    TextView wiseSayingView = findViewById(R.id.wise_sayings_of_time);
    TextView wiseSayingSpeakerView = findViewById(R.id.wise_sayings_of_time_speaker);

    String[] wiseSayingsOfTime = getResources().getStringArray(R.array.wise_sayings_of_time);
    int index = new Random().nextInt(wiseSayingsOfTime.length);
    String[] wiseSaying = wiseSayingsOfTime[index].split("-");

    wiseSayingView.setText(wiseSaying[0]);
    wiseSayingSpeakerView.setText(wiseSaying[1]);
    Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
    wiseSayingView.setAnimation(fadeInAnimation);
    wiseSayingSpeakerView.setAnimation(fadeInAnimation);
    startLoading();
  }

  private void startLoading() {
    Handler handler = new Handler();
    handler.postDelayed(() -> {
      Intent intent = new Intent(getBaseContext(), MainActivity.class);
      startActivity(intent);
      finish();
      overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }, 2000);
  }
}