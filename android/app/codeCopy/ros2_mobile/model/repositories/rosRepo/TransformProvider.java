package com.schneewittchen.ros2_mobile.model.repositories.rosRepo;

import org.ros.rosjava_geometry.FrameTransformTree;


public class TransformProvider {

    private static TransformProvider instance;

    private FrameTransformTree frameTransformTree;


    public TransformProvider() {
        this.reset();
    }


    public static TransformProvider getInstance() {
        if (instance == null)  {
            instance = new TransformProvider();
        }

        return instance;
    }

    public FrameTransformTree getTree() {
        return frameTransformTree;
    }

    public void reset() {
        this.frameTransformTree = new FrameTransformTree();
    }
}
