package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;
import android.view.MotionEvent;

import org.ros.namespace.GraphName;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;



public abstract class LayerView implements IBaseView {

    public static String TAG = LayerView.class.getSimpleName();

    protected BaseEntity widgetEntity;
    protected GraphName frame;
    protected VisualizationView parentView;


    public LayerView(Context context) {}


    public abstract void draw(VisualizationView view, GL10 gl);

    public void setFrame(GraphName frame) {
        this.frame = frame;
    }
    public GraphName getFrame() {
        return frame;
    }

    @Override
    public void setWidgetEntity(BaseEntity widgetEntity) {
        this.widgetEntity = widgetEntity;
    }

    @Override
    public BaseEntity getWidgetEntity() {
        return this.widgetEntity;
    }

    @Override
    public boolean sameWidgetEntity(BaseEntity other) {
        return other.id == this.widgetEntity.id;
    }

    public void setParentView(VisualizationView parentView) {
        this.parentView = parentView;
    }

    public void onSurfaceChanged(VisualizationView view, GL10 gl, int width, int height) {};

    public void onSurfaceCreated(VisualizationView view, GL10 gl, EGLConfig config) {};

    public boolean onTouchEvent(VisualizationView visualizationView, MotionEvent event) {
        return false;
    }
}
