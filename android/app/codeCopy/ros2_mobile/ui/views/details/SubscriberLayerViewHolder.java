package com.schneewittchen.ros2_mobile.ui.views.details;

import android.view.View;

import java.util.List;


public abstract class SubscriberLayerViewHolder extends DetailViewHolder {

    private LayerViewHolder layerViewHolder;
    private SubscriberViewHolder subscriberViewHolder;


    public SubscriberLayerViewHolder() {
        this.layerViewHolder = new LayerViewHolder(this);
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
        layerViewHolder.baseInitView(view);
        subscriberViewHolder.baseInitView(view);
    }

    public void baseBindEntity(BaseEntity entity) {
        layerViewHolder.baseBindEntity(entity);
        subscriberViewHolder.baseBindEntity(entity);
    }

    public void baseUpdateEntity(BaseEntity entity) {
        layerViewHolder.baseUpdateEntity(entity);
        subscriberViewHolder.baseUpdateEntity(entity);
    }
}
