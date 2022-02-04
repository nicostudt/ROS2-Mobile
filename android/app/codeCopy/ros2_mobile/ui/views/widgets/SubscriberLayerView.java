package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;

import org.ros.internal.message.Message;

import javax.microedition.khronos.opengles.GL10;


public abstract class SubscriberLayerView extends LayerView implements ISubscriberView{

    public SubscriberLayerView(Context context) {
        super(context);
    }


    @Override
    public void onNewMessage(Message message) {
        return;
    }

    @Override
    public void draw(VisualizationView view, GL10 gl) {}
}
