package com.schneewittchen.ros2_mobile.domain;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;


public class RosDomain {

    private static final String TAG = RosDomain.class.getSimpleName();

    // Singleton instance
    private static RosDomain mInstance;

    // Repositories
    private final ConfigRepository configRepository;
    private final RosRepository rosRepo;

    // Data objects
    private final LiveData<List<BaseEntity>> currentWidgets;
    private final LiveData<MasterEntity> currentMaster;


    private RosDomain(@NonNull Application application) {
        this.rosRepo = RosRepository.getInstance(application);
        this.configRepository = ConfigRepositoryImpl.getInstance(application);

        // React on config change and get the new data
        currentWidgets = Transformations.switchMap(configRepository.getCurrentConfigId(),
                configRepository::getWidgets);

        currentMaster = Transformations.switchMap(configRepository.getCurrentConfigId(),
                configRepository::getMaster);

        currentWidgets.observeForever(rosRepo::updateWidgets);
        currentMaster.observeForever(rosRepo::updateMaster);
    }


    public static RosDomain getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new RosDomain(application);
        }

        return mInstance;
    }


    public void publishData(BaseData data) {
        rosRepo.publishData(data);
    }

    public void createWidget(Long parentId, String widgetType) {
        new LambdaTask(() -> configRepository.createWidget(parentId, widgetType)).execute();
    }

    public void addWidget(Long parentId, BaseEntity widget) {
        configRepository.addWidget(parentId, widget);
    }

    public void updateWidget(Long parentId, BaseEntity widget) {
        configRepository.updateWidget(parentId, widget);
    }

    public void deleteWidget(Long parentId, BaseEntity widget) {
        configRepository.deleteWidget(parentId, widget);
    }

    public LiveData<BaseEntity> findWidget(long widgetId) {
        return configRepository.findWidget(widgetId);
    }

    public LiveData<List<BaseEntity>> getCurrentWidgets() {
        return this.currentWidgets;
    }

    public LiveData<RosData> getData(){ return this.rosRepo.getData(); }

    public void updateMaster(MasterEntity master) {
        configRepository.updateMaster(master);
    }

    public void setMasterDeviceIp(String deviceIp) {rosRepo.setMasterDeviceIp(deviceIp); }

    public void connectToMaster() {
        rosRepo.connectToMaster();
    }

    public void disconnectFromMaster() {
        rosRepo.disconnectFromMaster();
    }

    public LiveData<MasterEntity> getCurrentMaster() {
        return this.currentMaster;
    }

    public LiveData<ConnectionType> getRosConnection() {
        return rosRepo.getRosConnectionStatus();
    }

    public List<Topic> getTopicList() { return rosRepo.getTopicList(); }
}
