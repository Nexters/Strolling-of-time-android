package com.nexters.wiw.strolling_of_time.views.group;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.MakeMissionToolbar;
import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.adapter.GroupAdpater;
import com.nexters.wiw.strolling_of_time.views.adapter.MemberAdapter;
import com.nexters.wiw.strolling_of_time.views.adapter.MissionAdapter;
import com.nexters.wiw.strolling_of_time.views.main.MainActivity;
import com.nexters.wiw.strolling_of_time.views.timer.CerealTimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupMainActivity extends AppCompatActivity {

    private MemberAdapter memberAdapter;
    private MissionAdapter missionAdapter;

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

        init_member();
        init_mission();

    }

    private void init_member() {

        RecyclerView listview_group = findViewById(R.id.lv_member_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview_group.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("0");
        itemList.add("1");

        memberAdapter = new MemberAdapter(this, itemList, onClickItem);
        listview_group.setAdapter(memberAdapter);
    }

    private void init_mission() {

        RecyclerView listview_group = findViewById(R.id.lv_mission_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview_group.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("0");
        itemList.add("1");

//        missionAdapter = new MissionAdapter(this, itemList, onClickItem);

        missionAdapter = new MissionAdapter(this, itemList, v -> {
            Intent intent = new Intent(GroupMainActivity.this, CerealTimer.class);
            startActivity(intent);
        });

        listview_group.setAdapter(missionAdapter);

        //리스트 데코
//    MyListDecoration decoration = new MyListDecoration();
//    listview.addItemDecoration(decoration);
    }


    private View.OnClickListener onClickItem = v -> {
        String str = (String) v.getTag();
    };


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_make_mission:
                Intent intent = new Intent(this, MakeMissionActivity.class);
                startActivity(intent);
                break;
        }
    }



}
