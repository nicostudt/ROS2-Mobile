package com.schneewittchen.ros2_mobile.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;


@Dao
public abstract class SSHDao implements BaseDao<SSHEntity>{

    @Query("SELECT * FROM ssh_table WHERE configId = :configId LIMIT 1")
    abstract LiveData<SSHEntity> getSSH(long configId);

    @Query("DELETE FROM ssh_table WHERE configId = :configId")
    abstract void delete(long configId);

    @Query("DELETE FROM ssh_table")
    abstract void deleteAll();
}
