package com.schneewittchen.ros2_mobile.ui.fragments.viz;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.GestureDetectorCompat;

import com.schneewittchen.ros2_mobile.BuildConfig;
import com.schneewittchen.ros2_mobile.R;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseData;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.ui.general.DataListener;
import com.schneewittchen.ros2_mobile.ui.general.Position;
import com.schneewittchen.ros2_mobile.ui.views.widgets.IBaseView;
import com.schneewittchen.ros2_mobile.ui.views.widgets.IPublisherView;
import com.schneewittchen.ros2_mobile.ui.views.widgets.WidgetView;
import com.schneewittchen.ros2_mobile.utility.Constants;
import com.schneewittchen.ros2_mobile.utility.Utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WidgetViewGroup extends ViewGroup {

    public static final String TAG = "Log|" + WidgetViewGroup.class.getSimpleName();
    public static final int DEFAULT_TILES = 8;

    private final Paint crossPaint;
    private int tilesX;
    private int tilesY;
    private float tileWidth;
    private int crossColor;

    private boolean editing = true;
    private GridInteractor gridInteractor;
    public WidgetGridAdapter adapter;



    public WidgetViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs,
                        R.styleable.WidgetViewGroup, 0, 0);

        crossColor = a.getColor(R.styleable.WidgetViewGroup_crossColor,
                getResources().getColor(R.color.colorAccent));

        a.recycle();

        float stroke = Utils.dpToPx(getContext(), 1);

        crossPaint = new Paint();
        crossPaint.setColor(crossColor);
        crossPaint.setStrokeWidth(stroke);

        this.setWillNotDraw(false);

        adapter = new WidgetGridAdapter(this);
        gridInteractor = new GridInteractor(this);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return editing;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.gridInteractor.onTouchEvent(event);
    }

    private void calculateTiles() {
        float width = getWidth() - getPaddingLeft() - getPaddingRight();
        float height = getHeight() - getPaddingBottom() - getPaddingTop();

        if (width < height) {   // Portrait
            tilesX = DEFAULT_TILES;
            tileWidth = width / tilesX;
            tilesY = (int) (height / tileWidth);

        } else {    // Landscape
            tilesY = DEFAULT_TILES;
            tileWidth = height / tilesY;
            tilesX = (int) ( width / tileWidth);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View v = getChildAt(i);
            // this works because you set the dimensions of the ImageView to FILL_PARENT
            v.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(),
                    MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
                    getMeasuredHeight(), MeasureSpec.EXACTLY));
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Position all children within this layout.
     */
    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        calculateTiles();

        for (int i = 0; i < getChildCount(); i++) {
            positionChild(i);
        }
    }

    public void positionChild(int i) {
        final View child = getChildAt(i);

        // Check if view is visible
        if(child.getVisibility() == GONE)
            return;

        Position position = ((WidgetView) child).getPosition();

        // Y pos from bottom up
        int w = (int) (position.width * tileWidth);
        int h = (int) (position.height * tileWidth);
        int x = (int) (getPaddingLeft() + position.x * tileWidth);
        int y = (int) (getPaddingTop() + (tilesY - (position.height + position.y)) * tileWidth);

        // Place the child.
        child.layout(x, y, x + w, y + h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        float lineLen = Utils.dpToPx(getContext(), 5)/2;

        float startX = getPaddingLeft();
        float startY = getPaddingTop();

        // Draw x's
        float drawX = startX;
        for(int x = 0; x < tilesX +1; x++) {
            float drawY = startY;

            for(int y = 0; y < tilesY +1; y++) {
                canvas.drawLine(drawX-lineLen, drawY, drawX+lineLen, drawY, crossPaint);
                canvas.drawLine(drawX, drawY-lineLen, drawX, drawY+lineLen, crossPaint);

                drawY += tileWidth;
            }

            drawX += tileWidth;
        }
    }

    public void setEditMode(boolean flag) {
        this.editing = flag;
    }

    public int posToGrid(float pos) {
        return (int) (pos / tileWidth);
    }
}