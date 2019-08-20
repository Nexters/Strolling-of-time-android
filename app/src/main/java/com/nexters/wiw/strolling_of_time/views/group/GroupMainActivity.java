package com.nexters.wiw.strolling_of_time.views.group;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.adapter.GroupAdapter;
import com.nexters.wiw.strolling_of_time.views.adapter.MemberAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupMainActivity extends AppCompatActivity {
    
    // member list
    // mission list

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

        initMemberRecyclerView();
        init2();

    }

    private void initMemberRecyclerView() {

        RecyclerView memberRecyclerView = findViewById(R.id.lv_member_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        memberRecyclerView.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("0");
        itemList.add("1");
        itemList.add("2");
        itemList.add("3");
        itemList.add("4");
        itemList.add("5");
        itemList.add("6");
        itemList.add("7");
        itemList.add("8");
        itemList.add("9");
        itemList.add("10");
        itemList.add("11");

        MemberAdapter memberAdapter = new MemberAdapter(this, itemList, onClickItem);
        memberRecyclerView.setAdapter(memberAdapter);
    }

    private void init2() {

        ListView listview2 = findViewById(R.id.lv_group_list);

        ArrayList<String> itemList2 = new ArrayList<>();
        itemList2.add("0");
        itemList2.add("1");
    }

    private View.OnClickListener onClickItem = v -> {
        String str = (String) v.getTag();
        Toast.makeText(GroupMainActivity.this, str, Toast.LENGTH_SHORT).show();
        //        Intent intent = new Intent(this, GroupMainActivity.class);
//        startActivity(intent);
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
