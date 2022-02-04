package com.schneewittchen.ros2_mobile.model.repositories.rosRepo.connection;


public interface ConnectionListener {

    void onSuccess();
    void onFailed();

}