package com.schneewittchen.ros2_mobile.model.repositories;

import androidx.lifecycle.LiveData;


public interface SshRepository {

    void startSession();

    void stopSession();

    LiveData<Boolean> isConnected();

    void sendMessage(String message);

    void abort();

    LiveData<String> getOutputData();

    void updateSSH(SSHEntity ssh);

    LiveData<SSHEntity> getCurrentSSH();
}
