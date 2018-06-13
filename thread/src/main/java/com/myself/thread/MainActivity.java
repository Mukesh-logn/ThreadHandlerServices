package com.myself.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewThread viewThread = new ViewThread();
        WifyState wifyState = new WifyState();
        viewThread.start(); //this will call run function of ViewThread class

        wifyState.start();
        new Thread(new Task()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0 ;i<=2000 ;i++){
                    Log.d(getClass().getName(),"thread"+i);

                }
            }
        }).start();

    }
}
class Task implements Runnable{

    @Override
    public void run() {
        for(int i=0 ;i<=2000 ;i++){
            Log.i(getClass().getSimpleName(),"thread"+i);

        }
    }
}
