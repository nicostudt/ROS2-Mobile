package com.schneewittchen.ros2_mobile.ui.views.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.Nullable;

import com.schneewittchen.ros2_mobile.R;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.model.entities.widgets.IPositionEntity;
import com.schneewittchen.ros2_mobile.ui.general.Position;
import com.schneewittchen.ros2_mobile.utility.Utils;


public abstract class WidgetView extends ViewGroup implements IBaseView {

    public static String TAG = WidgetView.class.getSimpleName();

    protected Position position;
    protected BaseEntity widgetEntity;
    private float tileWidth;

    private Paint cornerPaint;
    private Paint sidePaint;
    private boolean currentInteraction;


    public WidgetView(Context context) {
        super(context, null);
        baseInit();
    }

    public WidgetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        baseInit();
    }


    private void baseInit() {
        setWillNotDraw(false);

        cornerPaint = new Paint();
        cornerPaint.setColor(getResources().getColor(R.color.colorPrimary));
        cornerPaint.setStyle(Paint.Style.STROKE);
        cornerPaint.setStrokeWidth(Utils.dpToPx(getContext(), 6));

        sidePaint = new Paint();
        sidePaint.setColor(getResources().getColor(R.color.colorPrimary));
        sidePaint.setStyle(Paint.Style.STROKE);
        sidePaint.setStrokeWidth(Utils.dpToPx(getContext(), 3));
    }

    public void onAfterDraw(Canvas canvas) {
        if (!currentInteraction) {
            return;
        }

        int w = getWidth();
        int w2 = w/2;
        int h = getHeight();
        int h2 = h/2;
        float lineD = 50;
        float sideD = 30;
        float sideD2 = sideD/2;

        // Draw interaction interface
        // Top left
        canvas.drawLine(0, 0, lineD, 0, cornerPaint);
        canvas.drawLine(0, 0, 0, lineD, cornerPaint);

        // Top right
        canvas.drawLine(w, 0, w - lineD, 0, cornerPaint);
        canvas.drawLine(w, 0, w, lineD, cornerPaint);

        // Bottom left
        canvas.drawLine(0, h, lineD, h, cornerPaint);
        canvas.drawLine(0, h, 0, h-lineD, cornerPaint);

        // Bottom right
        //canvas.drawLine(w, h, w-lineD, h, cornerPaint);
        //canvas.drawLine(w, h, w, h-lineD, cornerPaint);

        canvas.drawLine(w-30, h, w, h-30, sidePaint);
        canvas.drawLine(w-60, h, w, h-60, sidePaint);
        //canvas.drawLine(w, h, w, h-lineD, cornerPaint);

        // LEFT
        //canvas.drawRoundRect(w2-sideD, -sideD, w2+sideD, sideD, 10, 10, sidePaint);
        // TOP
        // RIGHT
        // BOTTOM

        //canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    public void updatePosition() {
        this.position = ((IPositionEntity)widgetEntity).getPosition();
    }

    public Position getPosition() {
        return this.position;
    }

    public void setCurrentInteraction(boolean flag) {
        currentInteraction = flag;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) { }


    @Override
    public void setWidgetEntity(BaseEntity widgetEntity) {
        this.widgetEntity = widgetEntity;
        this.updatePosition();
    }

    @Override
    public BaseEntity getWidgetEntity() {
        return this.widgetEntity;
    }


    @Override
    public boolean sameWidgetEntity(BaseEntity other) {
        return other.id == this.widgetEntity.id;
    }


}
