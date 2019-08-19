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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupMainActivity extends AppCompatActivity {

    private static final ArrayList<HashMap<String,String>> parentItems = new ArrayList<HashMap<String,String>>(); // 부모 리스트
    private static final ArrayList<ArrayList<HashMap<String,String>>> childItems = new ArrayList<ArrayList<HashMap<String,String>>>(); // 자식 리스트
    private int groupPosi;
    private int childPosi;

    private MemberAdapter memberAdapter;
    private GroupAdpater groupAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        ListView lv_group_list = (ListView)findViewById(R.id.lv_group_list);
        ScrollView group_scroll = (ScrollView)findViewById(R.id.group_scroll);

        lv_group_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                group_scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        init();
        init2();

    }

    private void init() {

        RecyclerView listview_group = findViewById(R.id.lv_member_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview_group.setLayoutManager(layoutManager);

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

        memberAdapter = new MemberAdapter(this, itemList, onClickItem);
        listview_group.setAdapter(memberAdapter);

//    MyListDecoration decoration = new MyListDecoration();
//    listview.addItemDecoration(decoration);
    }

    private void init2() {

        ListView listview2 = findViewById(R.id.lv_group_list);

        ArrayList<String> itemList2 = new ArrayList<>();
        itemList2.add("0");
        itemList2.add("1");

//        groupAdapter = new GroupAdapter(this, itemList, v -> {
//        });
//        listview2.setAdapter(groupAdapter);

//    MyListDecoration decoration = new MyListDecoration();
//    listview.addItemDecoration(decoration);
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
