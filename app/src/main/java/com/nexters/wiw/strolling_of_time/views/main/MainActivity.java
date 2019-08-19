package com.nexters.wiw.strolling_of_time.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.adapter.MissionAdapter;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView listview;
    private MissionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

//    ImageView img_main_groups = (ImageView)findViewById(R.id.img_main_groups);
//    TextView txt_make_group = (TextView)findViewById(R.id.txt_make_group);


    }
    private void init() {

        RecyclerView listview = findViewById(R.id.lv_mission);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

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

        adapter = new MissionAdapter(this, itemList, v -> {
            Intent intent = new Intent(MainActivity.this, GroupMainActivity.class);
            startActivity(intent);
        });
        listview.setAdapter(adapter);

//    MyListDecoration decoration = new MyListDecoration();
//    listview.addItemDecoration(decoration);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_profile:
                Intent intent = new Intent(this, GroupMainActivity.class);
                startActivity(intent);
                break;
//      case R.id.img_main_groups:
//        Intent intent = new Intent(this, GroupMainActivity.class);
//        startActivity(intent);
//        break;

            case R.id.txt_make_group:
//        Intent intent2 = new Intent(this, GroupMainActivity.class);
//        startActivity(intent2);
                break;
        }
    }
}
