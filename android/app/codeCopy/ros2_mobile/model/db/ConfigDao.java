package com.schneewittchen.ros2_mobile.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


@Dao
public abstract class ConfigDao implements BaseDao<ConfigEntity>{

    static final String TAG = ConfigDao.class.getCanonicalName();


    @Query("SELECT * FROM config_table")
    abstract LiveData<List<ConfigEntity>> getAllConfigs();

    @Query("SELECT * FROM config_table where id = :id")
    abstract LiveData<ConfigEntity> getConfig(long id);

    @Query("SELECT * FROM config_table ORDER BY creationTime DESC LIMIT 1")
    abstract LiveData<ConfigEntity> getLatestConfig();

    @Query("SELECT * FROM config_table ORDER BY creationTime DESC LIMIT 1")
    abstract ConfigEntity getLatestConfigDirect();

    @Query("DELETE FROM config_table where id = :id")
    abstract void removeConfig(long id);

    @Query("DELETE FROM config_table")
    abstract void deleteAll();

}