package com.schneewittchen.ros2_mobile.model.repositories.rosRepo.message;

import org.ros2.rcljava.interfaces.MessageDefinition;


public class RosData {

    private final Topic topic;
    private final MessageDefinition message;


    public RosData(Topic topic, MessageDefinition message) {
        this.topic = topic;
        this.message = message;
    }


    public Topic getTopic() {
        return this.topic;
    }

    public MessageDefinition getMessage() {
        return this.message;
    }
}
