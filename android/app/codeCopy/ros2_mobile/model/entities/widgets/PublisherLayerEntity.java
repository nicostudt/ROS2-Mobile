package com.schneewittchen.ros2_mobile.model.entities.widgets;


public abstract class PublisherLayerEntity extends BaseEntity
        implements I2DLayerEntity, IPublisherEntity {

    public float publishRate = 1f;
    public boolean immediatePublish = false;


    @Override
    public boolean equalRosState(BaseEntity other) {
        if (!super.equalRosState(other)) {
            return false;
        }

        if (!(other instanceof PublisherLayerEntity)) {
            return false;
        }

        PublisherLayerEntity otherPub = (PublisherLayerEntity) other;

        return this.publishRate == otherPub.publishRate
                && this.immediatePublish == otherPub.immediatePublish;
    }
}
