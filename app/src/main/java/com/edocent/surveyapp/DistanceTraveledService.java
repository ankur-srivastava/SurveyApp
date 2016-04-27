package com.edocent.surveyapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DistanceTraveledService extends Service {

    DistanceTravelBinder mDistanceTravelBinder = new DistanceTravelBinder();

    public DistanceTraveledService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class DistanceTravelBinder extends Binder{
        DistanceTravelBinder getBinder(){
            return DistanceTravelBinder.this;
        }
    }
}
