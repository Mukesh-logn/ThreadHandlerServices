package com.example.serviceaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class MainActivity extends AppCompatActivity {
    IPrintService iPrintService;
    PrintServiceConnection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent("com.example.serviceaidl.IPrintService");
        Intent bi = new Intent("com.example.serviceaidl.IPrintService");
        bi.setPackage("com.example.serviceaidl");
        conn = new PrintServiceConnection();
        bindService(bi, conn, Context.BIND_AUTO_CREATE);

    }

    public void print(View view) {
        try {
            if (iPrintService != null)
                iPrintService.basicTypes(1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    class PrintServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iPrintService = IPrintService.Stub.asInterface(iBinder);
            Log.d("service", "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iPrintService = null;
            Log.d("service", "dis-connected");
        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.d("service", "onBindingDied");
        }
    }
}
