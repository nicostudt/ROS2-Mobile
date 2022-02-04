package com.schneewittchen.ros2_mobile.model.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T obj);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(T obj);

    @Delete
    int delete(T obj);
}
