package com.schneewittchen.ros2_mobile.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "widget_table")
public class WidgetStorageData {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "type_name")
    @NonNull
    public String typeName;

    @ColumnInfo(name = "widget_config_id")
    @NonNull
    public long configId;

    @ColumnInfo(name = "data")
    @NonNull
    public String data;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;
}
