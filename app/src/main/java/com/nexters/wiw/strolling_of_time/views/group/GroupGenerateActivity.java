package com.nexters.wiw.strolling_of_time.views.group;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.R;

public class GroupGenerateActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_generate_group);
    initToolbar();
  }
  private void initToolbar(){
    TextView close_button = findViewById(R.id.close_button);
    close_button.setOnClickListener(v->{
      this.finish();
    });
  }
}
