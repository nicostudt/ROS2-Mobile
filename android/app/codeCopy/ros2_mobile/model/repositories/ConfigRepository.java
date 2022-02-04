package com.schneewittchen.ros2_mobile.model.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;


public interface ConfigRepository {

    void chooseConfig(long configId);

    void createConfig(String configName);

    void removeConfig(long configId);

    void updateConfig(ConfigEntity config);


    LiveData<List<ConfigEntity>> getAllConfigs();

    LiveData<Long> getCurrentConfigId();

    LiveData<ConfigEntity> getConfig(long id);

    LiveData<ConfigEntity> getCurrentConfig();


    void updateMaster(MasterEntity master);

    LiveData<MasterEntity> getMaster(long configId);


    void addWidget(Long parentId, BaseEntity widget);

    void createWidget(Long parentId, String widgetType);

    void deleteWidget(Long parentId, BaseEntity widget);

    void updateWidget(Long parentId, BaseEntity widget);

    LiveData<BaseEntity> findWidget(long widgetId);

    LiveData<List<BaseEntity>> getWidgets(long id);


    void updateSSH(SSHEntity ssh);

    void setSSH(SSHEntity ssh, String configId);

    LiveData<SSHEntity> getSSH(long configId);
}
