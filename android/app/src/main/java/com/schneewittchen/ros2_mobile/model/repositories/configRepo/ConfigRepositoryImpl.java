package com.schneewittchen.ros2_mobile.model.repositories.configRepo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;

import java.util.ArrayList;
import java.util.List;


public class ConfigRepositoryImpl extends ConfigRepository {

    private static final String TAG = "Log|" + ConfigRepositoryImpl.class.getSimpleName();
    private static ConfigRepositoryImpl mInstance;

    private final MutableLiveData<Long> currentConfigId;
    private final MutableLiveData<List<BaseEntity>> currentEntities;


    private ConfigRepositoryImpl(Application application) {
        currentEntities = new MutableLiveData<>();
        currentEntities.setValue(new ArrayList<>());

        currentConfigId = new MutableLiveData<>();
        currentConfigId.setValue(0L);
    }


    public static ConfigRepositoryImpl getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new ConfigRepositoryImpl(application);
        }

        return mInstance;
    }


    // CONFIGS -------------------------------------------------------------------------------------

    @Override
    public void chooseConfig(long newId) {
        Log.i(TAG, "chooseConfig " + newId);

        Long currentId = currentConfigId.getValue();

        if (currentId == null || currentId != newId) {
            currentConfigId.postValue(newId);
        }
    }

    @Override
    public void createConfig(String name) {
        Log.i(TAG, "createConfig " + name);
    }

    @Override
    public void updateConfig(ConfigEntity config) {
        Log.i(TAG, "updateConfig " + config);
    }

    @Override
    public void removeConfig(long configId) {
        Log.i(TAG, "removeConfig " + configId);
    }


    @Override
    public LiveData<Long> getCurrentConfigId() {
        return currentConfigId;
    }

    @Override
    public LiveData<ConfigEntity> getConfig(long id) {
        return null;
    }

    @Override
    public LiveData<ConfigEntity> getCurrentConfig() {
        return null;
    }

    @Override
    public LiveData<List<ConfigEntity>> getAllConfigs() {
        return null;
    }


    // WIDGETS -------------------------------------------------------------------------------------

    @Override
    public void addEntity(BaseEntity entity, Long parentId) {
        Log.i(TAG, "addEntity " + entity + " " + parentId);
        return;
    }

    public void createEntity(String entityType, Long parentId) {
        Log.i(TAG, "createEntity " + entityType + " " + parentId);

        BaseEntity newEntity = new EntityFactory()
                .createFromType(entityType)
                .createName(currentEntities.getValue())
                .setConfigId(currentConfigId.getValue())
                .build();

        List<BaseEntity> entities = currentEntities.getValue();
        entities.add(newEntity);
        currentEntities.setValue(entities);
    }

    @Override
    public void updateEntity(BaseEntity entity, Long parentId) {
        Log.i(TAG, "updateEntity " + entity + " " + parentId);
    }

    @Override
    public void deleteEntity(BaseEntity entity, Long parentId) {
        Log.i(TAG, "deleteEntity " + entity + " " + parentId);
    }

    @Override
    public LiveData<List<BaseEntity>> getEntities() {
        return currentEntities;
    }

    @Override
    public LiveData<BaseEntity> findEntity(long entityId) {
        return null;
    }

}
