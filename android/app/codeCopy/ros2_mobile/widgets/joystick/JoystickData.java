package com.schneewittchen.ros2_mobile.widgets.joystick;

import org.ros2.rcljava.publisher.Publisher;

import geometry_msgs.msg.Twist;
import geometry_msgs.msg.Vector3;
import org.ros2.rcljava.interfaces.MessageDefinition;

public class JoystickData extends BaseData {

    public static final String TAG = JoystickData.class.getSimpleName();
    public float x;
    public float y;


    public JoystickData(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public MessageDefinition toRosMessage(Publisher<MessageDefinition> publisher, BaseEntity widget) {
        JoystickEntity joyWidget = (JoystickEntity) widget;

        float xAxisValue = joyWidget.xScaleLeft  + (joyWidget.xScaleRight - joyWidget.xScaleLeft) * ((x+1) /2f);
        float yAxisValue = joyWidget.yScaleLeft  + (joyWidget.yScaleRight - joyWidget.yScaleLeft) * ((y+1) /2f);

        Twist message = new Twist();

        for (int i = 0; i < 2; i++) {
            String[] splitMapping = (i == 0? joyWidget.xAxisMapping : joyWidget.yAxisMapping).split("/");
            float value = i == 0? xAxisValue : yAxisValue;

            Vector3 dirVector;
            if (splitMapping[0].equals("Linear")) {
                dirVector = message.getLinear();
            } else {
                dirVector = message.getAngular();
            }

            switch (splitMapping[1]) {
                case "X":
                    dirVector.setX(value);
                    break;
                case "Y":
                    dirVector.setY(value);
                    break;
                case "Z":
                    dirVector.setZ(value);
                    break;
            }
        }

        return message;
    }
}
