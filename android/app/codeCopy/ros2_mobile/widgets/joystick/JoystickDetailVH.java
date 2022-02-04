package com.schneewittchen.ros2_mobile.widgets.joystick;

import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.schneewittchen.ros2_mobile.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import geometry_msgs.Twist;


public class JoystickDetailVH extends PublisherWidgetViewHolder {

    private Spinner xDirSpinner;
    private Spinner xAxisSpinner;
    private EditText xScaleLeft;
    private EditText xScaleRight;
    private TextView xScaleMiddle;

    private Spinner yDirSpinner;
    private Spinner yAxisSpinner;
    private EditText yScaleLeft;
    private EditText yScaleRight;
    private TextView yScaleMiddle;

    private ArrayAdapter<CharSequence> xDirAdapter;
    private ArrayAdapter<CharSequence> xAxisAdapter;
    private ArrayAdapter<CharSequence> yDirAdapter;
    private ArrayAdapter<CharSequence> yAxisAdapter;


    @Override
    public void initView(View view) {
        xDirSpinner = view.findViewById(R.id.xDirSpinner);
        xAxisSpinner = view.findViewById(R.id.xAxisSpinner);
        xScaleLeft = view.findViewById(R.id.xScaleLeft);
        xScaleRight = view.findViewById(R.id.xScaleRight);
        xScaleMiddle = view.findViewById(R.id.xScaleMiddle);

        yDirSpinner = view.findViewById(R.id.yDirSpinner);
        yAxisSpinner = view.findViewById(R.id.yAxisSpinner);
        yScaleLeft = view.findViewById(R.id.yScaleLeft);
        yScaleRight = view.findViewById(R.id.yScaleRight);
        yScaleMiddle = view.findViewById(R.id.yScaleMiddle);

        // Init spinner
        xDirAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.joystick_twist_dir, android.R.layout.simple_spinner_dropdown_item);
        xAxisAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.joystick_twist_axis, android.R.layout.simple_spinner_dropdown_item);
        yDirAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.joystick_twist_dir, android.R.layout.simple_spinner_dropdown_item);
        yAxisAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.joystick_twist_axis, android.R.layout.simple_spinner_dropdown_item);

        xDirSpinner.setAdapter(xDirAdapter);
        xAxisSpinner.setAdapter(xAxisAdapter);
        yDirSpinner.setAdapter(yDirAdapter);
        yAxisSpinner.setAdapter(yAxisAdapter);
    }

    @Override
    public void bindEntity(BaseEntity entity) {
        JoystickEntity widget = (JoystickEntity) entity;

        String[] xAxisMapping = widget.xAxisMapping.split("/");

        xDirSpinner.setSelection(xDirAdapter.getPosition(xAxisMapping[0]));
        xAxisSpinner.setSelection(xAxisAdapter.getPosition(xAxisMapping[1]));

        String[] yAxisMapping = widget.yAxisMapping.split("/");
        yDirSpinner.setSelection(yDirAdapter.getPosition(yAxisMapping[0]));
        yAxisSpinner.setSelection(yAxisAdapter.getPosition(yAxisMapping[1]));

        xScaleLeft.setText(String.format(Locale.US, "%.2f", widget.xScaleLeft));
        xScaleRight.setText(String.format(Locale.US, "%.2f", widget.xScaleRight));
        xScaleMiddle.setText(String.format(Locale.US, "%.2f", (widget.xScaleRight + widget.xScaleLeft) / 2));
        yScaleLeft.setText(String.format(Locale.US, "%.2f", widget.yScaleLeft));
        yScaleRight.setText(String.format(Locale.US, "%.2f", widget.yScaleRight));
        yScaleMiddle.setText(String.format(Locale.US, "%.2f", (widget.yScaleRight + widget.yScaleLeft) / 2));
    }


    @Override
    public void updateEntity(BaseEntity entity) {
        JoystickEntity widget = (JoystickEntity) entity;

        // Update joystick parameters
        widget.xAxisMapping = xDirSpinner.getSelectedItem() + "/" + xAxisSpinner.getSelectedItem();
        widget.yAxisMapping = yDirSpinner.getSelectedItem() + "/" + yAxisSpinner.getSelectedItem();

        for (String str: new String[]{"xScaleLeft", "xScaleRight", "yScaleLeft", "yScaleRight"}) {
            try {
                EditText editText = (EditText) this.getClass().getDeclaredField(str).get(this);

                assert editText != null;
                float value = Float.parseFloat(editText.getText().toString());

                widget.getClass().getField(str).set(entity, value);

            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public List<String> getTopicTypes() {
        return Collections.singletonList(Twist._TYPE);
    }
}
