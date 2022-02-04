package com.schneewittchen.ros2_mobile.ui.views.widgets;

import org.ros2.rcljava.interfaces.MessageDefinition;


public interface ISubscriberView {

     void onNewMessage(MessageDefinition message);
}
