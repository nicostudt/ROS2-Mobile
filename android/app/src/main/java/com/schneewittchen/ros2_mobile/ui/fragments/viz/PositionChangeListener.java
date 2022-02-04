package com.schneewittchen.ros2_mobile.ui.fragments.viz;


public abstract class PositionChangeListener {

    private int currentX, currentY;


    public PositionChangeListener(int startX, int startY) {
        this.currentX = startX;
        this. currentY = startY;
    }

    public void update(int newX, int newY) {
        int changeX = newX - currentX;
        int changeY = newY - currentY;
        currentX = newX;
        currentY = newY;

        if (changeX != 0 || changeY != 0) {
            onChange(changeX, changeY);
        }
    }

    public abstract void onChange(int dx, int dy);
}
