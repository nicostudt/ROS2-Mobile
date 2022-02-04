package com.schneewittchen.ros2_mobile.model.repositories.rosRepo.connection;

import android.os.AsyncTask;


public class ConnectionCheckTask extends AsyncTask<MasterEntity, Void, Boolean> {

    private static final int TIMEOUT_TIME = 2 * 1000;

    private final ConnectionListener listener;

    public ConnectionCheckTask(ConnectionListener listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(MasterEntity... masterEnts) {
        MasterEntity masterEnt = masterEnts[0];
        return Utils.isHostAvailable(masterEnt.ip, masterEnt.port, TIMEOUT_TIME);
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success)
            listener.onSuccess();
        else
            listener.onFailed();
    }
}
