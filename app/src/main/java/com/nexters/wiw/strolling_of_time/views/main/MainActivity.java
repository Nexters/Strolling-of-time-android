package com.nexters.wiw.strolling_of_time.views.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nexters.wiw.strolling_of_time.R;
import com.nexters.wiw.strolling_of_time.views.group.GroupMainActivity;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView img_main_groups = (ImageView)findViewById(R.id.img_main_groups);
    TextView txt_make_group = (TextView)findViewById(R.id.txt_make_group);

  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.img_main_groups:
        Intent intent = new Intent(this, GroupMainActivity.class);
        startActivity(intent);
        break;

      case R.id.txt_make_group:
//        Intent intent2 = new Intent(this, GroupMainActivity.class);
//        startActivity(intent2);
        break;
    }
  }
}
