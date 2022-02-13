package com.schneewittchen.ros2_mobile.model.repositories.rosRepo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class RosRepository  {

    private static final String TAG = "Log|" + RosRepository.class.getSimpleName();
    private static RosRepository instance;

    private final List<BaseEntity> currentWidgets;

    /**
     * Default private constructor. Initialize empty lists and maps of intern widgets and nodes.
     */
    private RosRepository(Context context) {
        this.currentWidgets = new ArrayList<>();
    }


    /**
     * Return the singleton instance of the repository.
     * @return Instance of this Repository
     */
    public static RosRepository getInstance(final Context context){
        if(instance == null){
            instance = new RosRepository(context);
        }

        return instance;
    }


    /**
     * React on a entity change. If at least one entity is added, deleted or changed this method
     * should be called.
     * @param newEntities Current list of entities
     */
    public void updateEntities(List<BaseEntity> newEntities) {
        Log.i(TAG, "Update widgets");

        this.currentWidgets.clear();
        for (BaseEntity entity: newEntities) {
            Log.i(TAG, "Add Entity: " + entity);
            this.currentWidgets.add(entity.copy());
        }
    }
    
}
