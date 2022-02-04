package com.schneewittchen.ros2_mobile.ui.general;

import android.text.Editable;
import android.text.TextWatcher;


public abstract class TextChangeListener<T> implements TextWatcher {

    private final T target;


    public TextChangeListener(T target) {
        this.target = target;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        return;
        // System.out.println("Before Text Changed: " + s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        return;
        // System.out.println("On Text Changed: " + s);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // System.out.println("After Text Changed: " + s.toString());
        this.onTextChanged(target, s);
    }

    public abstract void onTextChanged(T target, Editable s);
}