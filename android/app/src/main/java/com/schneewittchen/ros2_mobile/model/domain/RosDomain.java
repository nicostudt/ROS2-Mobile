package com.schneewittchen.ros2_mobile.model.domain;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseData;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.model.repositories.configRepo.ConfigRepository;
import com.schneewittchen.ros2_mobile.model.repositories.configRepo.ConfigRepositoryImpl;
import com.schneewittchen.ros2_mobile.model.repositories.rosRepo.RosRepository;

import java.util.List;


public class RosDomain {

    private static final String TAG = RosDomain.class.getSimpleName();

    // Singleton instance
    private static RosDomain mInstance;

    private ConfigRepository configRepository;
    private RosRepository rosRepository;


    private RosDomain(@NonNull Application application) {
        configRepository = ConfigRepositoryImpl.getInstance(application);
        rosRepository = RosRepository.getInstance(application);

        configRepository.getEntities().observeForever(rosRepository::updateEntities);
    }


    public static RosDomain getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new RosDomain(application);
        }

        return mInstance;
    }

    public LiveData<List<BaseEntity>> getCurrentEntities() {
        return configRepository.getEntities();
    }

    public void publishData(BaseData data) {
    }

    public void createWidget(String widgetType) {
        configRepository.createEntity(widgetType);
    }

    public void start() {
    }

    public void stop() {
    }

}
