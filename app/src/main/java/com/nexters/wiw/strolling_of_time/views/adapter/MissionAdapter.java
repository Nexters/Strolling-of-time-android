package com.nexters.wiw.strolling_of_time.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;
import com.nexters.wiw.strolling_of_time.views.main.MainActivity;

import java.util.ArrayList;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder> {

    private ArrayList<String> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public MissionAdapter(Context context, ArrayList<String> itemList, View.OnClickListener onClickItem) {
        this.context = context;
        this.itemList = itemList;
        this.onClickItem = onClickItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_main_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MissionAdapter.ViewHolder holder, int position) {
        String item = itemList.get(position);

        holder.mission_list.setOnClickListener(view -> {
            this.onClickItem.onClick(view);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView mission_list;

        public ViewHolder(View itemView) {
            super(itemView);

            mission_list = itemView.findViewById(R.id.mission_list);

        }
    }
}