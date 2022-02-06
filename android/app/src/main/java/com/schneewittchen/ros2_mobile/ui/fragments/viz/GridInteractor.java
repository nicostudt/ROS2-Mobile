package com.schneewittchen.ros2_mobile.ui.fragments.viz;

import android.content.Context;
import android.graphics.Rect;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;

import com.schneewittchen.ros2_mobile.model.entities.widgets.IPositionEntity;
import com.schneewittchen.ros2_mobile.ui.general.Position;
import com.schneewittchen.ros2_mobile.ui.views.widgets.WidgetView;

public class GridInteractor {

    public static final String TAG = "Log|" + GridInteractor.class.getSimpleName();

    private GestureDetectorCompat mDetector;
    private GestureListener gestureListener;
    private WidgetViewGroup widgetViewGroup;
    Vibrator vibrator;

    private boolean isDragging;
    private float startDragX;
    private float startDragY;
    private int draggedViewId;
    private PositionChangeListener posListener;


    public GridInteractor(WidgetViewGroup widgetViewGroup) {
        this.widgetViewGroup = widgetViewGroup;

        Context context = widgetViewGroup.getContext();
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        gestureListener = new GestureListener();
        this.mDetector = new GestureDetectorCompat(context, gestureListener);
    }


    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_UP){
            endDrag();

        } else if(ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (isDragging) {
                drag(ev);
            }
        }

        return mDetector.onTouchEvent(ev);
    }

    private void startDrag(float initX, float initY, int viewId) {
        Log.i(TAG, "startDrag");
        isDragging = true;
        startDragX = initX;
        startDragY = initY;
        draggedViewId = viewId;

        WidgetView view = (WidgetView)widgetViewGroup.getChildAt(viewId);

        view.setCurrentInteraction(true);
        vibrator.vibrate(20);

        posListener = new PositionChangeListener(0, 0) {
            @Override
            public void onChange(int dx, int dy) {
                Position pos = view.getPosition();
                pos.x += dx;
                pos.y -= dy;
                widgetViewGroup.positionChild(draggedViewId);
            }
        };

    }

    private void drag(MotionEvent ev) {
        //Log.i(TAG, "Drag");
        // Determine grid movement
        float distX = ev.getX() - startDragX;
        float distY = ev.getY() - startDragY;

        int gridX = widgetViewGroup.posToGrid(distX);
        int gridY = widgetViewGroup.posToGrid(distY);

        posListener.update(gridX, gridY);
    }

    private void endDrag() {
        if (isDragging) {
            Log.i(TAG, "endDrag");
            ((WidgetView)widgetViewGroup.getChildAt(draggedViewId)).setCurrentInteraction(false);
        }

        isDragging = false;
    }


    class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            float pressX = motionEvent.getX();
            float pressY = motionEvent.getY();

            // Check childs
            for (int i = 0; i < widgetViewGroup.getChildCount(); i++) {
                View child = widgetViewGroup.getChildAt(i);

                Rect hitRect = new Rect();
                child.getHitRect(hitRect);

                if (hitRect.contains((int)pressX, (int)pressY)) {
                    startDrag(pressX, pressY, i);
                    break;
                }
            }
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

    }

}
