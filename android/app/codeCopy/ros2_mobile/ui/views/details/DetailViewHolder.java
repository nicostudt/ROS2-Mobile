package com.schneewittchen.ros2_mobile.ui.views.details;

import android.view.View;


public abstract class DetailViewHolder implements IBaseViewHolder{

    protected BaseEntity entity;
    protected View itemView;
    protected DetailsViewModel viewModel;
    private WidgetChangeListener widgetChangeListener;

    protected abstract void initView(View itemView);
    protected abstract void bindEntity(BaseEntity entity);
    protected abstract void updateEntity(BaseEntity entity);

    public abstract void baseInitView(View itemView);
    public abstract void baseBindEntity(BaseEntity entity);
    public abstract void baseUpdateEntity(BaseEntity entity);


    /**
     * Call this method internally to update the bound widget info
     * and subsequently force an update of the widget list.
     */
    public void forceWidgetUpdate() {
        baseUpdateEntity(entity);
        updateEntity(entity);
        widgetChangeListener.onWidgetDetailsChanged(entity);
    }

    public void setViewModel(DetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setEntity(BaseEntity entity) {
        this.entity = entity.copy();
        baseBindEntity(this.entity);
        bindEntity(this.entity);
    }

    public BaseEntity getEntity() {
        return this.entity;
    }

    public void setView(View view) {
        this.itemView = view;
        baseInitView(view);
        initView(view);
    }

    public void setWidgetChangeListener(WidgetChangeListener changeListener) {
        this.widgetChangeListener = changeListener;
    }
}
