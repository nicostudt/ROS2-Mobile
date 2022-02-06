package com.schneewittchen.ros2_mobile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.schneewittchen.ros2_mobile.model.domain.RosDomain;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseData;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.model.repositories.rosRepo.message.RosData;

import java.util.List;

public class VizViewModel extends AndroidViewModel {

    private static final String TAG = "TAG|" + VizViewModel.class.getSimpleName();

    private final RosDomain rosDomain;
    private MutableLiveData<Boolean> editModeEnabled;


    public VizViewModel(@NonNull Application application) {
        super(application);

        rosDomain = RosDomain.getInstance(application);
        editModeEnabled = new MutableLiveData<>(true);
    }


    public void startRosConnection() {
        this.editModeEnabled.setValue(false);
        this.rosDomain.start();
    }

    public void stopRosConnection() {
        this.editModeEnabled.setValue(true);
        this.rosDomain.stop();
    }

    public void createWidget(String widgetType) {
        this.rosDomain.createWidget(widgetType);
    }

    public LiveData<List<BaseEntity>> getWidgets() {
        return rosDomain.getCurrentWidgets();
    }

    public void publishData(BaseData data) {
        rosDomain.publishData(data);
    }

    public LiveData<Boolean> getEditMode() {
        return editModeEnabled;
    }


}