package com.example.boundservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;

import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by M1030452 on 3/1/2018.
 */

public class BoundService extends Service {
    private final IBinder iBinder = new LocalBoundService();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return iBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
        return (dateformat.format(new Date()));
    }

    class LocalBoundService extends Binder {
        BoundService getServices() {
            return BoundService.this;
        }

    }
}
