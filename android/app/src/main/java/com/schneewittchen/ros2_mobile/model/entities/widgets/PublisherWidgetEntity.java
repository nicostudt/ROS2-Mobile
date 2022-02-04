package com.schneewittchen.ros2_mobile.model.entities.widgets;


import com.schneewittchen.ros2_mobile.ui.general.Position;


public abstract class PublisherWidgetEntity
        extends BaseEntity
        implements IPositionEntity, IPublisherEntity{

    public float publishRate = 1f;
    public boolean immediatePublish = false;
    public int posX;
    public int posY;
    public int width;
    public int height;


    @Override
    public boolean equalRosState(BaseEntity other) {
        if (!super.equalRosState(other)) {
            return false;
        }

        if (!(other instanceof PublisherWidgetEntity)) {
            return false;
        }

        PublisherWidgetEntity otherPub = (PublisherWidgetEntity) other;

        return this.publishRate == otherPub.publishRate
                && this.immediatePublish == otherPub.immediatePublish;
    }

    @Override
    public Position getPosition() {
        return new Position(posX, posY, width, height);
    }

    @Override
    public void setPosition(Position position) {
        this.posX = position.x;
        this.posY = position.y;
        this.width = position.width;
        this.height = position.height;
    }
}
