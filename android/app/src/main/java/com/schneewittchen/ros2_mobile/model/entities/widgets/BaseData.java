package com.schneewittchen.ros2_mobile.model.entities.widgets;

import com.schneewittchen.ros2_mobile.model.repositories.rosRepo.message.Topic;

import org.ros2.rcljava.interfaces.MessageDefinition;
import org.ros2.rcljava.publisher.Publisher;


public abstract class BaseData {

    protected Topic topic;


    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public MessageDefinition toRosMessage(Publisher<MessageDefinition> publisher, BaseEntity widget){
        return null;
    }
}
