package com.schneewittchen.ros2_mobile.ui.fragments.viz;

import android.view.GestureDetector;
import android.view.MotionEvent;

public abstract class InteractionDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    public static final String TAG = "Log|" + InteractionDetector.class.getSimpleName();

    public abstract void onLongPress(MotionEvent motionEvent);
    public abstract boolean onDoubleTap(MotionEvent motionEvent);

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }


    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
