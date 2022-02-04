package com.schneewittchen.ros2_mobile.model.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "widget_count_table")
public class WidgetCountEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "widget_config_id")
    @NonNull
    public long configId;

    @ColumnInfo(name = "widget_type")
    public String type;

    @ColumnInfo(name = "widget_count")
    public long count;
}
