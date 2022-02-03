package com.schneewittchen.ros2_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.schneewittchen.ros2_mobile.databinding.VizFragmentBinding;


public class VizFragment extends Fragment {

    private static final String TAG = "Log|" + VizFragment.class.getSimpleName();

    private VizFragmentBinding binding;
    private VizViewModel mViewModel;


    public static VizFragment newInstance() {
        return new VizFragment();
    }

    public VizFragment() {
        super(R.layout.viz_fragment);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(VizViewModel.class);

        binding = VizFragmentBinding.bind(view);
        binding.toolbar.setOnMenuItemClickListener(this::onMenuItemClicked);
    }


    public boolean onMenuItemClicked(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toggleRunning)  {
            Log.i(TAG, "Toggle running");
            return true;

        } else if (id == R.id.addWidget) {
            Log.i(TAG, "Add widget");
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}