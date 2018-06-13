package com.example.boundservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    BoundService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                BoundService.LocalBoundService binder = (BoundService.LocalBoundService) iBinder;
                myService = binder.getServices();
                isBound = true;
                myService.getCurrentTime();
                Log.d("time",myService.getCurrentTime());
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                isBound = false;
            }
        };
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);


    }
}
