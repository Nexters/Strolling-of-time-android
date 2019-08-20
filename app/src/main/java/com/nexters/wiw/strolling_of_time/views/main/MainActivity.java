package com.nexters.wiw.strolling_of_time.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.adapter.MissionAdapter;
import com.nexters.wiw.strolling_of_time.views.group.GroupGenerateActivity;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initRecyclerView();
  }

  private void initRecyclerView() {
    RecyclerView recyclerView = findViewById(R.id.lv_mission);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    recyclerView.setLayoutManager(layoutManager);

    ArrayList<String> groups = new ArrayList<>();
    groups.add("0");

    MissionAdapter adapter = new MissionAdapter(this, groups, v -> {
      Intent intent = new Intent(MainActivity.this, GroupMainActivity.class);
      startActivity(intent);
    });
    recyclerView.setAdapter(adapter);
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.iv_profile:
        break;
      case R.id.group_card:
        Intent groupMainActivity = new Intent(this, GroupMainActivity.class);
        startActivity(groupMainActivity);
        break;

      case R.id.txt_make_group:
        Intent groupGenerateActivityIntent = new Intent(this, GroupGenerateActivity.class);
        startActivity(groupGenerateActivityIntent);
        break;
    }
  }
}
