package com.schneewittchen.ros2_mobile.ui.fragments.viz;

import android.content.Context;
import android.util.Log;

import com.schneewittchen.ros2_mobile.BuildConfig;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseData;
import com.schneewittchen.ros2_mobile.model.entities.widgets.BaseEntity;
import com.schneewittchen.ros2_mobile.ui.general.DataListener;
import com.schneewittchen.ros2_mobile.ui.views.widgets.IBaseView;
import com.schneewittchen.ros2_mobile.ui.views.widgets.IPublisherView;
import com.schneewittchen.ros2_mobile.ui.views.widgets.WidgetView;
import com.schneewittchen.ros2_mobile.utility.Constants;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WidgetGridAdapter {

    public static final String TAG = "Log|" + WidgetGridAdapter.class.getSimpleName();

    private WidgetViewGroup viewGroup;
    private DataListener dataListener;
    private List<BaseEntity> widgetList;


    public WidgetGridAdapter(WidgetViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.widgetList = new ArrayList<>();
    }


    public void setDataListener(DataListener listener) {
        this.dataListener = listener;
    }

    public void informDataChange(BaseData data) {
        if (dataListener != null) {
            dataListener.onNewWidgetData(data);
        }
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
            this.viewGroup.requestLayout();
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
            this.viewGroup.addView((WidgetView)baseView);
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
            object = constructor.newInstance(this.viewGroup.getContext());

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

        for(int i = 0; i < this.viewGroup.getChildCount(); i++) {
            IBaseView view = (IBaseView) this.viewGroup.getChildAt(i);

            if (view.sameWidgetEntity(entity)) {
                view.setWidgetEntity(entity);
                return;
            }
        }
    }

    private void removeViewFor(BaseEntity entity) {
        Log.i(TAG, "Remove view for " + entity.name);

        for(int i = 0; i < this.viewGroup.getChildCount(); i++) {
            IBaseView view = (IBaseView) this.viewGroup.getChildAt(i);

            if (view.sameWidgetEntity(entity)) {
                this.viewGroup.removeView((WidgetView)view);
                return;
            }
        }
    }
}
