package com.nexters.wiw.strolling_of_time.views.timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nexters.wiw.strolling_of_time.R;

public class CoworkerBottomDialogFragment extends BottomSheetDialogFragment {

  public static CoworkerBottomDialogFragment newInstance() {
    return new CoworkerBottomDialogFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.bottom_sheet_coworker, container,
            false);


    return view;

  }
}