package com.schneewittchen.ros2_mobile.ui.views.widgets;

import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;


public interface IBaseView {

    void setWidgetEntity(BaseEntity entity);
    BaseEntity getWidgetEntity();

    boolean sameWidgetEntity(BaseEntity other);
}
