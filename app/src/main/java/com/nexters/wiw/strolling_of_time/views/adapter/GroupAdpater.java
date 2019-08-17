package com.nexters.wiw.strolling_of_time.views.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.*;


public class GroupAdpater extends CursorAdapter {
    LayoutInflater inflater;
    public static Cursor cursor;

    public GroupAdpater(Context context, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cursor = c;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View listItemLayout = inflater.inflate(R.layout.mission_item, parent, false);
        return listItemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mission_title = (TextView) view.findViewById(R.id.mission_title);
        TextView mission_time = (TextView) view.findViewById(R.id.mission_time);
        TextView mission_remain_time = (TextView) view.findViewById(R.id.mission_remain_time);

        mission_title.setText("hello");
        mission_time.setText("hello");
        mission_remain_time.setText("hello");

//        mission_title.setText(cursor.getString(1));
//        mission_time.setText(cursor.getString(2));
//        mission_remain_time.setText(cursor.getString(3));


//        date += cursor.getString(2) + "/" + cursor.getString(3) + "/" + cursor.getString(4);
//        tvDate.setText(date);
//        tvDiary.setText(cursor.getString(1));
//        tvFeel.setText(cursor.getString(5));

    }
}
