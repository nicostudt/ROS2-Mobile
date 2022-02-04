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
import androidx.core.view.MotionEventCompat;

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
    int crossColor;

    private boolean editing = true;
    private InteractionDetector interactionDetector;
    private GestureDetectorCompat mDetector;

    private List<BaseEntity> widgetList;
    DataListener dataListener;


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

        this.widgetList = new ArrayList<>();

        this.interactionDetector = new InteractionDetector() {

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onDoubleTap(MotionEvent motionEvent) {
                return false;
            }
        };

        this.mDetector = new GestureDetectorCompat(this.getContext(), interactionDetector);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return editing;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.mDetector.onTouchEvent(event);
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

    private void positionChild(int i) {
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

    public void setWidgets(List<BaseEntity> newWidgets) {
        boolean changes = false;

        // Create widget check with ids
        HashMap<Long, Boolean> widgetCheckMap = new HashMap<>();
        HashMap<Long, BaseEntity> widgetEntryMap = new HashMap<>();

        for (BaseEntity oldWidget: this.widgetList) {
            widgetCheckMap.put(oldWidget.id, false);
            widgetEntryMap.put(oldWidget.id, oldWidget);
        }

        for (BaseEntity newWidget: newWidgets) {
            if (widgetCheckMap.containsKey(newWidget.id)) {
                widgetCheckMap.put(newWidget.id, true);

                // Check if widget has changed
                BaseEntity oldWidget = widgetEntryMap.get(newWidget.id);

                if (!oldWidget.equals(newWidget)){
                    changeViewFor(newWidget);
                    changes = true;
                }

            } else{
                addViewFor(newWidget);
                changes = true;
            }
        }

        // Delete unused widgets
        for (Long id: widgetCheckMap.keySet()) {
            if (!widgetCheckMap.get(id)) {
                removeViewFor(widgetEntryMap.get(id));
                changes = true;
            }
        }

        this.widgetList.clear();
        this.widgetList.addAll(newWidgets);

        if (changes) {
            requestLayout();
        }
    }



    private void addViewFor(BaseEntity entity) {
        Log.i(TAG, "Add view for " + entity);

        IBaseView baseView = createViewFrom(entity);

        if (baseView == null) return;

        baseView.setWidgetEntity(entity);

        // Check if view is a group view and register the sub layers

        /*
        if (baseView instanceof WidgetGroupView) {
            WidgetGroupView groupView = (WidgetGroupView) baseView;

            for (BaseEntity subEntity: entity.childEntities)  {
                IBaseView subView = createViewFrom(subEntity);
                subView.setWidgetEntity(subEntity);

                if (!(subView instanceof LayerView))
                    return;

                groupView.addLayer((LayerView)subView);
            }

        }*/

        // Set data listener if view is a publisher
        if (baseView instanceof IPublisherView) {
            ((IPublisherView)baseView).setDataListener(this::informDataChange);
        }

        // Add as subview if the view is a widget view
        if (baseView instanceof WidgetView) {
            this.addView((WidgetView)baseView);
        }

    }

    private IBaseView createViewFrom(BaseEntity entity) {
        Log.i(TAG, "Create View from " + entity);

        // Create actual widget view object
        String classPath = BuildConfig.APPLICATION_ID
                + String.format(Constants.VIEW_FORMAT, entity.type.toLowerCase(), entity.type);


        Object object;

        try {
            Class<?> clazz = Class.forName(classPath);
            Constructor<?> constructor = clazz.getConstructor(Context.class);
            object = constructor.newInstance(this.getContext());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        Log.i(TAG, "object is a : " + object.getClass().getCanonicalName());


        if (!(object instanceof IBaseView)) {
            Log.i(TAG, "View can not be created from: " + classPath);
            return null;
        }

        return (IBaseView) object;
    }

    private void changeViewFor(BaseEntity entity) {
        Log.i(TAG, "Change view for " + entity.name);

        for(int i = 0; i < this.getChildCount(); i++) {
            IBaseView view = (IBaseView) this.getChildAt(i);

            if (view.sameWidgetEntity(entity)) {
                view.setWidgetEntity(entity);
                return;
            }
        }
    }

    private void removeViewFor(BaseEntity entity) {
        Log.i(TAG, "Remove view for " + entity.name);

        for(int i = 0; i < this.getChildCount(); i++) {
            IBaseView view = (IBaseView) this.getChildAt(i);

            if (view.sameWidgetEntity(entity)) {
                this.removeView((WidgetView)view);
                return;
            }
        }
    }

    public void informDataChange(BaseData data) {
        if (dataListener != null) {
            dataListener.onNewWidgetData(data);
        }
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
}