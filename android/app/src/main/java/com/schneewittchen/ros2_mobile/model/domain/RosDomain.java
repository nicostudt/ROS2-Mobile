package com.schneewittchen.ros2_mobile.model.domain;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseData;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;

import java.util.ArrayList;
import java.util.List;


public class RosDomain {

    private static final String TAG = RosDomain.class.getSimpleName();

    // Singleton instance
    private static RosDomain mInstance;

    private RosDomain(@NonNull Application application) {
    }


    public static RosDomain getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new RosDomain(application);
        }

        return mInstance;
    }

    public LiveData<List<BaseEntity>> getCurrentWidgets() {
        return new MutableLiveData<List<BaseEntity>>(new ArrayList<>());
    }

    public void publishData(BaseData data) {
    }

    public void createWidget(String widgetType) {
    }

    public void start() {
    }

    public void stop() {
    }

}
