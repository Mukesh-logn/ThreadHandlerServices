package com.myself.thread;

import android.util.Log;

/**
 * Created by M1030452 on 6/17/2017.
 */

class ViewThread extends Thread{
    @Override
    public void run() {
        super.run();
        // run block can be called as a thread..
        for(int i=0 ;i<=200 ;i++){
            Log.i(getClass().getSimpleName(),"thread"+i);

        }
    }
}
