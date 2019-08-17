package com.nexters.wiw.strolling_of_time.views.timer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nexters.wiw.strolling_of_time.R;

public class CoworkerBottomDialogFragment extends BottomSheetDialogFragment {

    RecyclerView recyclerView;

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

        initCoworkerList(view);

        return view;
    }

    private void initCoworkerList(View view){
        CoworkerBottomDialogAdapter adapter = new CoworkerBottomDialogAdapter();
        recyclerView = view.findViewById(R.id.coworker_list);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this.getContext(), LinearLayout.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
