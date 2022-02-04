package com.schneewittchen.ros2_mobile.ui.views.details;

import android.view.View;

import java.util.List;


public abstract class SubscriberWidgetViewHolder extends DetailViewHolder {

    private WidgetViewHolder widgetViewHolder;
    private SubscriberViewHolder subscriberViewHolder;


    public SubscriberWidgetViewHolder() {
        this.widgetViewHolder = new WidgetViewHolder(this);
        this.subscriberViewHolder = new SubscriberViewHolder(this);
        this.subscriberViewHolder.topicTypes = this.getTopicTypes();
    }


    public abstract List<String> getTopicTypes();

    @Override
    public void setViewModel(DetailsViewModel viewModel) {
        super.setViewModel(viewModel);
        subscriberViewHolder.viewModel = viewModel;
    }

    public void baseInitView(View view) {
        widgetViewHolder.baseInitView(view);
        subscriberViewHolder.baseInitView(view);
    }

    public void baseBindEntity(BaseEntity entity) {
        widgetViewHolder.baseBindEntity(entity);
        subscriberViewHolder.baseBindEntity(entity);
    }

    public void baseUpdateEntity(BaseEntity entity) {
        widgetViewHolder.baseUpdateEntity(entity);
        subscriberViewHolder.baseUpdateEntity(entity);
    }
}
