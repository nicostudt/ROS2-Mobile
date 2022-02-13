package com.schneewittchen.ros2_mobile;

import org.junit.Test;

import static org.junit.Assert.*;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.model.repositories.configRepo.EntityFactory;
import com.schneewittchen.ros2_mobile.utility.Constants;
import com.schneewittchen.ros2_mobile.widgets.joystick.JoystickEntity;

import java.util.ArrayList;
import java.util.Locale;


public class EntityFactoryTest {

    @Test
    public void test_empty_build() {
        BaseEntity entity = new EntityFactory()
                .build();

        assertNull(entity);
    }

    @Test
    public void test_create_from_wrong_type() {
        String wrongType = "NotAvailable";
        BaseEntity entity = new EntityFactory()
                .createFromType(wrongType)
                .build();

        assertNull(entity);
    }

    @Test
    public void test_create_from_type() {
        String type = "Joystick";
        BaseEntity entity = new EntityFactory()
                .createFromType(type)
                .build();

        assertNotNull(entity);

        long creationTime = entity.creationTime;

        assertEquals(creationTime, entity.id);
        assertEquals(creationTime, entity.creationTime);
        assertNull(entity.name);
        assertEquals(0, entity.configId);
    }

    @Test
    public void test_create_name_first_available() {
        ArrayList<BaseEntity> entityList = new ArrayList<>();
        BaseEntity entity = new JoystickEntity();
        entity.name = "Joy";
        entityList.add(entity);

        String type = "Joystick";
        BaseEntity testEntity = new EntityFactory()
                .createFromType(type)
                .createName(entityList)
                .build();

        int count = 1;
        String expectedName = String.format(Locale.ENGLISH, Constants.WIDGET_NAMING, type, count);
        System.out.println("Expected name: " + expectedName);

        assertNotNull(testEntity);
        assertEquals(expectedName, testEntity.name);
    }

    @Test
    public void test_create_name_occupied() {
        String type = "Joystick";
        ArrayList<BaseEntity> entityList = new ArrayList<>();

        int count = 5;

        for (int i = 1; i < count; i++) {
            BaseEntity entity = new JoystickEntity();
            entity.name = String.format(Locale.ENGLISH, Constants.WIDGET_NAMING, type, i);
            entityList.add(entity);
        }

        BaseEntity testEntity = new EntityFactory()
                .createFromType(type)
                .createName(entityList)
                .build();

        String expectedName = String.format(Locale.ENGLISH, Constants.WIDGET_NAMING, type, count);
        System.out.println("Expected name: " + expectedName);

        assertNotNull(testEntity);
        assertEquals(expectedName, testEntity.name);
    }

    @Test
    public void test_set_config_id() {
        long configId = 123L;
        String type = "Joystick";

        BaseEntity entity = new EntityFactory()
                .createFromType(type)
                .setConfigId(123)
                .build();

        assertNotNull(entity);
        assertEquals(configId, entity.configId);
    }

}
