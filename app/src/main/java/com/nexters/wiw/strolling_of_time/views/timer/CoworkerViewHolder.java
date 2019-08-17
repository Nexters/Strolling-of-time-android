package com.nexters.wiw.strolling_of_time.views.timer;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.R;

import de.hdodenhof.circleimageview.CircleImageView;

class CoworkerViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView coworkerProfileImage;
    private TextView coworkerName;

    CoworkerViewHolder(@NonNull View itemView) {
        super(itemView);
        coworkerProfileImage = itemView.findViewById(R.id.coworker_profile_image);
        coworkerName = itemView.findViewById(R.id.coworker_name);
    }

    void setCoworkerProfileImage(String profileImage){
        coworkerProfileImage.setImageURI(Uri.parse(profileImage));
    }

    void setCoworkerName(String coworkerName){
        this.coworkerName.setText(coworkerName);
    }
}
