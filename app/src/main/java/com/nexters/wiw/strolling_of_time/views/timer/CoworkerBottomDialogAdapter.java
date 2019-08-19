package com.nexters.wiw.strolling_of_time.views.timer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.domain.User;

import java.util.ArrayList;
import java.util.List;

public class CoworkerBottomDialogAdapter extends RecyclerView.Adapter<CoworkerViewHolder>{
    private List<User> coworkers = new ArrayList<>();
    @NonNull
    @Override
    public CoworkerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bottom_sheet_coworker_item, viewGroup, false);
        return new CoworkerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CoworkerViewHolder coworkerViewHolder, int i) {
        if(!coworkers.isEmpty()) {
            User user = coworkers.get(i);
            coworkerViewHolder.setCoworkerName(user.getNickname());
            coworkerViewHolder.setCoworkerProfileImage(user.getProfileImage());
        }
    }

    @Override
    public int getItemCount() {
        return coworkers.size();
    }

    public void setCoworkers(List<User> coworkers){
        this.coworkers = coworkers;
        notifyDataSetChanged();
    }
}
