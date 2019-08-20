package com.nexters.wiw.strolling_of_time.views.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nexters.wiw.strolling_of_time.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_sign_up:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;

//            case R.id.txt_make_group:
//        Intent intent2 = new Intent(this, GroupMainActivity.class);
//        startActivity(intent2);
//                break;
        }
    }
}
