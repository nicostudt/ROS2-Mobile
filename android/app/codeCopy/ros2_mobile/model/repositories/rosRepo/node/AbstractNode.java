package com.schneewittchen.ros2_mobile.model.repositories.rosRepo.node;

import android.util.Log;

import org.ros.namespace.GraphName;
import org.ros.node.ConnectedNode;
import org.ros.node.Node;
import org.ros.node.NodeMain;


public class AbstractNode implements NodeMain {

    public static final String TAG = AbstractNode.class.getSimpleName();

    protected Topic topic;
    protected BaseEntity widget;


    @Override
    public void onStart(ConnectedNode parentNode) {
        Log.i(TAG, "On Start:  " + topic.name);
    }

    @Override
    public void onShutdown(Node node) {
        Log.i(TAG, "On Shutdown:  " + topic.name);
    }

    @Override
    public void onShutdownComplete(Node node) {
        Log.i(TAG, "On Shutdown Complete: " + topic.name);
    }

    @Override
    public void onError(Node node, Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of(topic.name);
    }


    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setWidget(BaseEntity widget) {
        this.widget = widget;
        this.setTopic(widget.topic);
    }

}
