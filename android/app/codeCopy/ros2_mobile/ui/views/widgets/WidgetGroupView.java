package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import org.ros.internal.message.Message;



public abstract class WidgetGroupView extends WidgetView implements ISubscriberView, IPublisherView {


    public WidgetGroupView(Context context) {
        super(context);
    }

    public WidgetGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void addLayer(LayerView layer);

    public abstract void onNewData(RosData data);

    @Override
    public void onNewMessage(Message message) { return; }
}
