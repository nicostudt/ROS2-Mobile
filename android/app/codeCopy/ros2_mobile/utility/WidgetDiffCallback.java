package com.schneewittchen.ros2_mobile.utility;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;


public class WidgetDiffCallback extends DiffUtil.Callback{

    public static String TAG = WidgetDiffCallback.class.getSimpleName();

    List<BaseEntity> oldWidgets;
    List<BaseEntity> newWidgets;


    public WidgetDiffCallback(List<BaseEntity> newWidgets, List<BaseEntity> oldWidgets) {
        this.newWidgets = newWidgets;
        this.oldWidgets = oldWidgets;
    }


    @Override
    public int getOldListSize() {
        return oldWidgets.size();
    }

    @Override
    public int getNewListSize() {
        return newWidgets.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        BaseEntity oldWidget = oldWidgets.get(oldItemPosition);
        BaseEntity newWidget = newWidgets.get(newItemPosition);

        return oldWidget.id == newWidget.id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        BaseEntity oldWidget = oldWidgets.get(oldItemPosition);
        BaseEntity newWidget = newWidgets.get(newItemPosition);

        return oldWidget.equals(newWidget);
    }

}
