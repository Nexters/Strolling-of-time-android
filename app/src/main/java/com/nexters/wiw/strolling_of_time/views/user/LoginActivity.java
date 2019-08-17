package com.nexters.wiw.strolling_of_time.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_sign_up:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                break;

//            case R.id.txt_make_group:
//        Intent intent2 = new Intent(this, GroupMainActivity.class);
//        startActivity(intent2);
//                break;
        }
    }
}
