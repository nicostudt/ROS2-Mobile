package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;


public abstract class PublisherWidgetView extends WidgetView implements IPublisherView{

    public static String TAG = PublisherWidgetView.class.getSimpleName();

    private DataListener dataListener;


    public PublisherWidgetView(Context context) {
        super(context);
    }

    public PublisherWidgetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void publishViewData(BaseData data) {
        if(dataListener == null) return;

        data.setTopic(widgetEntity.topic);
        dataListener.onNewWidgetData(data);
    }

    @Override
    public void setDataListener(DataListener listener) {
        this.dataListener = listener;
    }

}
