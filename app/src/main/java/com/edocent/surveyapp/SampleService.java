package com.edocent.surveyapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class SampleService extends IntentService {
    private static final String TAG = SampleService.class.getSimpleName();

    public SampleService() {
        super("SampleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "In onHandleIntent. Message will be printed after 10sec");
        if (intent != null) {
            synchronized (this){
                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.v(TAG, "Service started");
            }
        }
    }
}
