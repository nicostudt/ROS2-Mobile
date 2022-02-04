package com.schneewittchen.ros2_mobile.model.repositories.rosRepo.node;

import org.ros.internal.message.Message;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;


public class SubNode extends AbstractNode {

    private final NodeListener listener;


    public SubNode(NodeListener listener) {
        this.listener = listener;
    }


    @Override
    public void onStart(ConnectedNode parentNode) {
        super.onStart(parentNode);

        try {
            if (this.widget != null) {
                this.widget.validMessage = true;
            }

            Subscriber<? extends Message> subscriber = parentNode.newSubscriber(topic.name, topic.type);

            subscriber.addMessageListener(data -> {
                listener.onNewMessage(new RosData(topic, data));
            });

        } catch(Exception e) {
            if (this.widget != null) {
                this.widget.validMessage = false;
            }
            e.printStackTrace();
        }

    }

    public interface NodeListener  {
        void onNewMessage(RosData message);
    }
}
