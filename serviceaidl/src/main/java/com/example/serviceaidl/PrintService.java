package com.example.serviceaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by M1030452 on 2/27/2018.
 */

public class PrintService extends Service {
    static final String TAG = "Logger";
    IPrintService.Stub mBinder=new IPrintService.Stub() {
        @Override
        public void basicTypes(int anInt) throws RemoteException {

        }

        @Override
        public void printS(String s) throws RemoteException {

        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;

    }


}
