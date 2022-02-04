package com.schneewittchen.ros2_mobile.ui.views.details;

import android.view.View;


interface IBaseViewHolder {

    void baseInitView(View view);
    void baseBindEntity(BaseEntity entity);
    void baseUpdateEntity(BaseEntity entity);
}
