package com.nexters.wiw.strolling_of_time.views.group;

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

import com.nexters.wiw.strolling_of_time.R;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupMainActivity extends AppCompatActivity {

    private static final ArrayList<HashMap<String,String>> parentItems = new ArrayList<HashMap<String,String>>(); // 부모 리스트
    private static final ArrayList<ArrayList<HashMap<String,String>>> childItems = new ArrayList<ArrayList<HashMap<String,String>>>(); // 자식 리스트
    private int groupPosi;
    private int childPosi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

// 부모 리스트에 요소 추가
        HashMap<String, String> groupA = new HashMap<String, String>();
        groupA.put("group", "식물");
        HashMap<String, String> groupB = new HashMap<String, String>();
        groupB.put("group", "동물");

        parentItems.add(groupA);
        parentItems.add(groupB);
        parentItems.add(groupB);
        parentItems.add(groupB);

        // 자식 리스트에 요소 추가 1
        final ArrayList<HashMap<String, String>> childListA = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> childAA = new HashMap<String, String>();
        childAA.put("group", "식물");
        childAA.put("name", "해바라기");
        HashMap<String, String> childAB = new HashMap<String, String>();
        childAB.put("group", "식물");
        childAB.put("name", "강아지풀");
        HashMap<String, String> childAC = new HashMap<String, String>();
        childAC.put("group", "식물");
        childAC.put("name", "라벤더");

        childListA.add(childAA);
        childListA.add(childAB);
        childListA.add(childAC);

        childItems.add(childListA);

        // 자식 리스트에 요소 추가 2
        final ArrayList<HashMap<String, String>> childListB = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> childBA = new HashMap<String, String>();
        childBA.put("group", "동물");
        childBA.put("name", "호랑이");
        HashMap<String, String> childBB = new HashMap<String, String>();
        childBB.put("group", "동물");
        childBB.put("name", "사자");
        HashMap<String, String> childBC = new HashMap<String, String>();
        childBC.put("group", "동물");
        childBC.put("name", "코끼리");

        childListB.add(childBA);
        childListB.add(childBB);
        childListB.add(childBC);

        childItems.add(childListB);


        childItems.add(childListA);


        childItems.add(childListA);


        final ScrollView group_scroll = (ScrollView)findViewById(R.id.group_scroll);


        // ExpandableListView 생성
        final ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.lv_group_list);
        // 부모 리스트와 자식 리스트를 포함한 adapter를 생성
        final SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, parentItems, android.R.layout.simple_expandable_list_item_1, new String[] {"group"},
                new int[] { android.R.id.text1 }, childItems, android.R.layout.simple_list_item_single_choice, new String[] { "name", "group" }, new int[] { android.R.id.text1, android.R.id.text2 });
        // 어댑터 설정
        expandableListView.setAdapter(adapter);

        // 자식 리스트에 클릭 이벤트 구현
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // groupPosition: 선택한 자식 아이템이 속한 부모 리스트의 위치
                // childPosition: 부모 리스트 내 선택한 자식 아이템의 위치
                Toast.makeText(getApplicationContext(), parentItems.get(groupPosition) + " : " + childItems.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                groupPosi = groupPosition;
                childPosi = childPosition;
                Integer plantCount = adapter.getChildrenCount(0); // 0 번째 그룹(식물)의 자료 갯수
                int position = groupPosition*(plantCount + 1) + childPosition + 1; // 체크 설정 or 해제할 아이템 위치

                if(expandableListView.isItemChecked(position)){
                    expandableListView.setItemChecked(position, false); // 체크 해제
                }else{
                    expandableListView.setItemChecked(position, true); // 체크 설정
                }

                return false;
            }
        });

//        expandableListView.setOnTouchListener( new expandableListView.OnTouchListener() {
//            @Override
//            public boolean onTouch( View v, MotionEvent event )
//            {
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//
//        expandableListView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                group_scroll.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

//        // 버튼 이벤트
//        Button addButton = (Button)findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "추가", Toast.LENGTH_SHORT).show();
//                Integer plantCount = adapter.getChildrenCount(0); // 0 번째 그룹(식물)의 자료 갯수
//                // 아이템 추가
//                HashMap<String, String> newChild = new HashMap<String, String>();
//                newChild.put("group", "식물");
//                newChild.put("name", "추가 " + plantCount);
//                childListA.add(newChild);
//                // 갱신
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        Button addButton2 = (Button)findViewById(R.id.addButton2);
//        addButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "추가", Toast.LENGTH_SHORT).show();
//                Integer animalCount = adapter.getChildrenCount(1); // 1 번째 그룹(동물)의 자료 갯수
//                // 아이템 추가
//                HashMap<String, String> newChild = new HashMap<String, String>();
//                newChild.put("group", "식물");
//                newChild.put("name", "추가 " + animalCount);
//                childListB.add(newChild);
//                // 갱신
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//        Button modiButton = (Button)findViewById(R.id.modiButton);
//        modiButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "수정", Toast.LENGTH_SHORT).show();
//                if(groupPosi == 0) { // 선택된 아이템이 식물일 때
//                    // 선택한 아이템 수정
//                    String str = childListA.get(childPosi).values().toString().substring(1, childListA.get(childPosi).values().toString().indexOf(","));
//                    HashMap<String, String> newChild = new HashMap<String, String>();
//                    newChild.put("group", "식물");
//                    newChild.put("name", str + " 수정!");
//                    childListA.set(childPosi, newChild);
//                    // 선택 초기화
//                    expandableListView.clearChoices();
//                    // 갱신
//                    adapter.notifyDataSetChanged();
//                }
//                else{ // 선택된 아이템이 동물일 때
//                    // 선택한 아이템 수정
//                    String str = childListB.get(childPosi).values().toString().substring(1, childListB.get(childPosi).values().toString().indexOf(","));
//                    HashMap<String, String> newChild = new HashMap<String, String>();
//                    newChild.put("group", "동물");
//                    newChild.put("name", str + " 수정!");
//                    childListB.set(childPosi, newChild);
//                    // 선택 초기화
//                    expandableListView.clearChoices();
//                    // 갱신
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
//
//        Button delButton = (Button)findViewById(R.id.delButton);
//        delButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "삭제", Toast.LENGTH_SHORT).show();
//                if(groupPosi == 0) { // 선택된 아이템이 식물일 때
//                    // 선택한 아이템 삭제
//                    childListA.remove(childPosi);
//                    // 선택 초기화
//                    expandableListView.clearChoices();
//                    // 갱신
//                    adapter.notifyDataSetChanged();
//                }
//                else{ // 선택된 아이템이 동물일 때
//                    // 선택한 아이템 삭제
//                    childListB.remove(childPosi);
//                    // 선택 초기화
//                    expandableListView.clearChoices();
//                    // 갱신
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });
    }

}
