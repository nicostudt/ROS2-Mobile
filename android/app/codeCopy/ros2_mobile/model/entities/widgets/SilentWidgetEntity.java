package com.schneewittchen.ros2_mobile.model.entities.widgets;


public abstract class SilentWidgetEntity extends BaseEntity implements ISilentEntity, IPositionEntity {

    public int posX;
    public int posY;
    public int width;
    public int height;


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
