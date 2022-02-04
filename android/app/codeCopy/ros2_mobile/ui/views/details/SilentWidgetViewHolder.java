package com.schneewittchen.ros2_mobile.ui.views.details;

import android.view.View;


public abstract class SilentWidgetViewHolder extends DetailViewHolder {

    private WidgetViewHolder widgetViewHolder;


    public SilentWidgetViewHolder() {
        this.widgetViewHolder = new WidgetViewHolder(this);
    }


    public void baseInitView(View view) {
        widgetViewHolder.baseInitView(view);
    }

    public void baseBindEntity(BaseEntity entity) {
        widgetViewHolder.baseBindEntity(entity);
    }

    public void baseUpdateEntity(BaseEntity entity) {
        widgetViewHolder.baseUpdateEntity(entity);
    }
}
