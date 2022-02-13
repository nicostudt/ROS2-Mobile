package com.schneewittchen.ros2_mobile.model.repositories.configRepo;

import android.util.Log;

import androidx.annotation.Nullable;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.utility.Constants;
import com.schneewittchen.ros2_mobile.utility.Utils;

import java.util.List;
import java.util.Locale;

public class EntityFactory {

    public static final String TAG = "TAG|" + EntityFactory.class.getSimpleName();

    private BaseEntity mEntity;


    public EntityFactory() {
        mEntity = null;
    }


    /**
     * Create actual entity from entity type string via reflection.
     *
     * @param entityType Name of the entity e.g. Joystick
     */
    public EntityFactory createFromType(String entityType) {
        long timeMS = System.currentTimeMillis();

        String classPath = String.format(Constants.ENTITY_FORMAT, entityType.toLowerCase(), entityType);
        Object object = Utils.getObjectFromClassName(classPath);

        if (!(object instanceof BaseEntity)) {
            Log.i(TAG, "Entity can not be created from: " + classPath);
            return this;
        }

        mEntity = (BaseEntity) object;
        mEntity.id = timeMS;
        mEntity.creationTime = timeMS;
        mEntity.type = entityType;

        return this;
    }

    public EntityFactory createName(List<BaseEntity> entityList) {
        String entityType = mEntity.type;
        String newName = "";

        // Create numbered names e.g. Joystick1 ... JoystickX and check if its available
        for (int count = 1; count > 0; count++) {
            newName = String.format(Locale.ENGLISH, Constants.WIDGET_NAMING, entityType, count);

            boolean foundCollision = false;
            for (BaseEntity entity: entityList) {
                if (entity.name.equals(newName)) {
                    foundCollision = true;
                    break;
                }
            }

            if (!foundCollision) {
                break;
            }
        }

        mEntity.name = newName;
        return this;
    }

    public EntityFactory setConfigId(long configId) {
        this.mEntity.configId = configId;
        return this;
    }

    public BaseEntity build() {
        return mEntity;
    }
}
