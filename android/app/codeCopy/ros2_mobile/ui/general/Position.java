package com.schneewittchen.ros2_mobile.ui.general;


public class Position {

    public int x;
    public int y;
    public int width;
    public int height;


    public Position() {
        this(0, 0, 0, 0);
    }

    public Position(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
