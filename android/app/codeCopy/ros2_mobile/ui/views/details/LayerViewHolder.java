package com.schneewittchen.ros2_mobile.ui.views.details;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.schneewittchen.ros2_mobile.R;


class LayerViewHolder implements IBaseViewHolder, TextView.OnEditorActionListener {

    private EditText nameEdittext;
    private DetailViewHolder parentViewHolder;


    public LayerViewHolder(DetailViewHolder parentViewHolder) {
        this.parentViewHolder = parentViewHolder;
    }


    @Override
    public void baseInitView(View view) {
        nameEdittext = view.findViewById(R.id.name_edit_text);
        nameEdittext.setOnEditorActionListener(this);
    }

    @Override
    public void baseBindEntity(BaseEntity entity) {
        nameEdittext.setText(entity.name);
    }

    @Override
    public void baseUpdateEntity(BaseEntity entity) {
        entity.name = nameEdittext.getText().toString();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
            case EditorInfo.IME_ACTION_NEXT:
            case EditorInfo.IME_ACTION_PREVIOUS:
                Utils.hideSoftKeyboard(v);
                v.clearFocus();
                parentViewHolder.forceWidgetUpdate();
                return true;
        }

        return false;
    }
}
