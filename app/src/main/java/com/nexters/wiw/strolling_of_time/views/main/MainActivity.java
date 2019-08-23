package com.nexters.wiw.strolling_of_time.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.adapter.GroupAdpater;
import com.nexters.wiw.strolling_of_time.views.adapter.MissionAdapter;
import com.nexters.wiw.strolling_of_time.views.group.GroupGenerateActivity;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private GroupAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
    private void init() {

        RecyclerView listview = findViewById(R.id.lv_mission);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("0");
        itemList.add("1");

        adapter = new GroupAdpater(this, itemList, v -> {
            Intent intent = new Intent(MainActivity.this, GroupMainActivity.class);
            startActivity(intent);
        });
        listview.setAdapter(adapter);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_profile:
                Intent intent = new Intent(this, GroupMainActivity.class);
                startActivity(intent);
                break;

            case R.id.txt_make_group:
        Intent intent2 = new Intent(this, GroupGenerateActivity.class);
        startActivity(intent2);
                break;


        }
    }
}
