package com.schneewittchen.ros2_mobile.utility;

import android.os.AsyncTask;


public class LambdaTask extends AsyncTask<Void, Void, Void> {

    TaskRunnable taskRunnable;


    public LambdaTask(TaskRunnable taskRunnable){
        this.taskRunnable = taskRunnable;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        taskRunnable.run();
        return null;
    }

    public interface TaskRunnable {
        void run();
    }
}
