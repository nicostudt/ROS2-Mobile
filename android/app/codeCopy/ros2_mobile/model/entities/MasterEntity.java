package com.schneewittchen.ros2_mobile.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "master_table")
public class MasterEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public long configId;
    public String ip = "192.168.0.0";
    public int port = 11311;
}
