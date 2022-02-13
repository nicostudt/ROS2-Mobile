package com.schneewittchen.ros2_mobile.ui.fragments.viz;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import com.schneewittchen.ros2_mobile.R;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.viewmodel.VizViewModel;
import com.schneewittchen.ros2_mobile.databinding.VizFragmentBinding;
import com.schneewittchen.ros2_mobile.widgets.joystick.JoystickEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class VizFragment extends Fragment {

    private static final String TAG = "Log|" + VizFragment.class.getSimpleName();

    private VizFragmentBinding binding;
    private VizViewModel mViewModel;

    List<BaseEntity> widgetList = new ArrayList<>();


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
        binding.widgetMenu.setNavigationItemSelectedListener(this::onMenuItemClicked);

        mViewModel.getWidgets().observe(getViewLifecycleOwner(), widgetEntities -> {
            binding.widgetGroupview.adapter.setWidgets(widgetEntities);
        });

        mViewModel.getEditMode().observe(getViewLifecycleOwner(), this::changeViewMode);
    }


    public boolean onMenuItemClicked(@NonNull MenuItem item) {
        Log.i(TAG, "Clicked " + item.getItemId());

        int id = item.getItemId();

        if (id == R.id.run)  {
            mViewModel.startRosConnection();

        } else if (id == R.id.stop) {
            mViewModel.stopRosConnection();

        } else if (id == R.id.openDrawer) {
            toggleDrawer();

        } else if (id == R.id.joystickItem) {
            mViewModel.createWidget("Joystick");
            binding.vizDrawer.closeDrawer(Gravity.RIGHT);

        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

    /**
     * Change view mode to running process or edit mode
     * @param editModeEnabled Flag if edit mode is enabled
     */
    private void changeViewMode(boolean editModeEnabled) {
        // Lock/unlock menu items
        MenuItem openItem = binding.toolbar.getMenu().findItem(R.id.openDrawer);
        MenuItem runItem = binding.toolbar.getMenu().findItem(R.id.run);
        MenuItem stopItem = binding.toolbar.getMenu().findItem(R.id.stop);

        openItem.setVisible(editModeEnabled);
        runItem.setVisible(editModeEnabled);
        stopItem.setVisible(!editModeEnabled);

        // Lock/unlock menu drawer
        int lockMode = editModeEnabled ? DrawerLayout.LOCK_MODE_UNLOCKED:
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;

        binding.vizDrawer.setDrawerLockMode(lockMode);

        // Change mode of widget group view
        binding.widgetGroupview.setEditMode(editModeEnabled);
    }

    private void toggleDrawer() {
        DrawerLayout drawerLayout = binding.vizDrawer;
        int side = Gravity.RIGHT;

        if (drawerLayout.isDrawerOpen(side)) {
            drawerLayout.closeDrawer(side);

        } else {
            drawerLayout.openDrawer(side);
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

}