package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import javax.microedition.khronos.opengles.GL10;


public abstract class PublisherLayerView extends LayerView implements IPublisherView{

    private DataListener dataListener;


    public PublisherLayerView(Context context) {
        super(context);
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

    @Override
    public void draw(VisualizationView view, GL10 gl) {}
}
