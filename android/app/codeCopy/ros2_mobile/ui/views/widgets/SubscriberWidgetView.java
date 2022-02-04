package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;
import android.util.AttributeSet;

import org.ros.internal.message.Message;



public abstract class SubscriberWidgetView extends WidgetView implements ISubscriberView{


    public SubscriberWidgetView(Context context) {
        super(context);
    }

    public SubscriberWidgetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onNewMessage(Message message) {
        return;
    }

}
