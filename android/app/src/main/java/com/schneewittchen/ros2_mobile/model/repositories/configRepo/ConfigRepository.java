package com.schneewittchen.ros2_mobile.model.repositories.configRepo;

import androidx.lifecycle.LiveData;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;

import java.util.List;


public abstract class ConfigRepository {

    public abstract void chooseConfig(long configId);

    public abstract void createConfig(String configName);

    public abstract void updateConfig(ConfigEntity config);

    public abstract void removeConfig(long configId);


    public abstract LiveData<List<ConfigEntity>> getAllConfigs();

    public abstract LiveData<Long> getCurrentConfigId();

    public abstract LiveData<ConfigEntity> getConfig(long id);

    public abstract LiveData<ConfigEntity> getCurrentConfig();


    public void addEntity(BaseEntity entity) {
        this.addEntity(entity, null);
    }

    public void createEntity(String entityType) {
        this.createEntity(entityType, null);
    }

    public void updateEntity(BaseEntity entity) {
        this.updateEntity(entity, null);
    }

    public void deleteEntity(BaseEntity entity) {
        this.deleteEntity(entity, null);
    }


    public abstract void addEntity(BaseEntity widget, Long parentId);

    public abstract void createEntity(String entityType, Long parentId);

    public abstract void deleteEntity(BaseEntity entity, Long parentId);

    public abstract void updateEntity(BaseEntity entity, Long parentId);


    public abstract LiveData<BaseEntity> findEntity(long entityId);

    public abstract LiveData<List<BaseEntity>> getEntities();

}
