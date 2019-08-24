package com.nexters.wiw.strolling_of_time.views.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nexters.wiw.strolling_of_time.R;

public class MakeMissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_mission);
        initToolbar();
    }

    private void initToolbar(){
        ImageView close_button = findViewById(R.id.iv_close_make_mission);
        close_button.setOnClickListener(v->{
            this.finish();
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
