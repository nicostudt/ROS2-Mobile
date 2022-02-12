package com.schneewittchen.ros2_mobile.ui.views.widgets;


import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseData;
import com.schneewittchen.ros2_mobile.ui.general.DataListener;


public interface IPublisherView {

    void publishViewData(BaseData data);
    void setDataListener(DataListener listener);
}
