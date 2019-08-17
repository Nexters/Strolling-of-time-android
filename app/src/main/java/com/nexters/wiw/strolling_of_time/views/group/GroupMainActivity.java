package com.nexters.wiw.strolling_of_time.views.group;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.nexters.wiw.strolling_of_time.MakeMissionToolbar;
import com.nexters.wiw.strolling_of_time.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupMainActivity extends AppCompatActivity {

    private static final ArrayList<HashMap<String,String>> parentItems = new ArrayList<HashMap<String,String>>(); // 부모 리스트
    private static final ArrayList<ArrayList<HashMap<String,String>>> childItems = new ArrayList<ArrayList<HashMap<String,String>>>(); // 자식 리스트
    private int groupPosi;
    private int childPosi;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        ListView lv_group_list = findViewById(R.id.lv_group_list);
        ScrollView group_scroll = findViewById(R.id.group_scroll);

        lv_group_list.setOnTouchListener((view, motionEvent) -> {
            group_scroll.requestDisallowInterceptTouchEvent(true);
            return false;
        });


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_make_mission:
                Intent intent = new Intent(this, MakeMissionActivity.class);
                startActivity(intent);
                break;
        }
    }



}
