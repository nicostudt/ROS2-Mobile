package com.schneewittchen.ros2_mobile.ui.views.widgets;


public interface IBaseView {

    void setWidgetEntity(BaseEntity entity);
    BaseEntity getWidgetEntity();

    boolean sameWidgetEntity(BaseEntity other);
}
