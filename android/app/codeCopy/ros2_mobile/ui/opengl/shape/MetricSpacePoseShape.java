/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.schneewittchen.ros2_mobile.ui.opengl.shape;

import com.schneewittchen.rosandroid.ui.opengl.visualisation.ROSColor;

/**
 * Represents a pose.
 *
 * @author damonkohler@google.com (Damon Kohler)
 */
public class MetricSpacePoseShape extends TriangleFanShape {

    private static final ROSColor COLOR = ROSColor.fromHexAndAlpha("377dfa", 1.0f);
    private static final float VERTICES[] = {
            0.2f, 0.f, 0.f,
            -0.2f, -0.15f, 0.f,
            -0.05f, 0.f, 0.f,
            -0.2f, 0.15f, 0.f,
            0.2f, 0.f, 0.f
    };

    public MetricSpacePoseShape() {
        super(VERTICES, COLOR);
    }
}
