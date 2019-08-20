package com.nexters.wiw.strolling_of_time.views.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

import com.nexters.wiw.strolling_of_time.R;


public class GroupAdapter extends CursorAdapter {
    private LayoutInflater inflater;

    public GroupAdapter(Context context, Cursor c) {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.activity_main_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mission_title = view.findViewById(R.id.mission_title);
        TextView mission_time = view.findViewById(R.id.mission_time);
        TextView mission_remain_time = view.findViewById(R.id.mission_remain_time);

        mission_title.setText("타이틀");
        mission_time.setText("시간");
        mission_remain_time.setText("남은 시간");

    }
}
