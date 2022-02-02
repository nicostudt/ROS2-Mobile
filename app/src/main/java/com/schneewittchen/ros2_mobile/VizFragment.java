package com.schneewittchen.ros2_mobile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class VizFragment extends Fragment {

    private VizViewModel mViewModel;

    public static VizFragment newInstance() {
        return new VizFragment();
    }


    public VizFragment() {
        super(R.layout.viz_fragment);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(VizViewModel.class);
    }

}