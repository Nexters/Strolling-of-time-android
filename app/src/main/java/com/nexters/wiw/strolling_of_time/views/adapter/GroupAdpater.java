package com.nexters.wiw.strolling_of_time.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;
import com.nexters.wiw.strolling_of_time.views.main.MainActivity;

import java.util.ArrayList;

public class GroupAdpater extends RecyclerView.Adapter<GroupAdpater.ViewHolder> {

    private ArrayList<String> itemList;
    private Context context;
    private View.OnClickListener onClickItem;

    public GroupAdpater(Context context, ArrayList<String> itemList, View.OnClickListener onClickItem) {
        this.context = context;
        this.itemList = itemList;
        this.onClickItem = onClickItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_mission_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = itemList.get(position);

        holder.img_main_groups.setOnClickListener(view -> {
            this.onClickItem.onClick(view);
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button img_main_groups;

        public ViewHolder(View itemView) {
            super(itemView);

            img_main_groups = itemView.findViewById(R.id.img_main_groups);

        }
    }
}