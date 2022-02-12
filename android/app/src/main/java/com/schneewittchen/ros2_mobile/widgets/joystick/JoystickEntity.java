package com.schneewittchen.ros2_mobile.widgets.joystick;

import com.schneewittchen.ros2_mobile.model.entities.widgets.PublisherWidgetEntity;
import com.schneewittchen.ros2_mobile.model.repositories.rosRepo.message.Topic;

import geometry_msgs.msg.Twist;


public class JoystickEntity extends PublisherWidgetEntity {

    public String xAxisMapping;
    public String yAxisMapping;
    public float xScaleLeft;
    public float xScaleRight;
    public float yScaleLeft;
    public float yScaleRight;


    public JoystickEntity() {
        this.width = 4;
        this.height = 4;
        this.topic = new Topic("cmd_vel", Twist.class.getSimpleName());
        this.immediatePublish = false;
        this.publishRate = 20f;
        this.xAxisMapping = "Angular/Z";
        this.yAxisMapping = "Linear/X";
        this.xScaleLeft = 1;
        this.xScaleRight = -1;
        this.yScaleLeft = -1;
        this.yScaleRight = 1;
    }

}
