package com.myself.threadandhandler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView timer1Text, timer2Text;
    private Handler update1 = new Handler();
    private Handler update2 = new Handler();
    private int i, j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer1Text = (TextView) findViewById(R.id.timer_1);
        timer2Text = (TextView) findViewById(R.id.timer_2);
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                for (i = 0; i <= 1000; i++) {
                    Log.v("Thread1", String.valueOf(i));
                    update1.post(new Runnable() {
                        @Override
                        public void run() {
                            timer1Text.setText("" + i);
                        }
                    });
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();
                for (j = 0; j <= 1000; j++) {
                    update2.post(new Runnable() {
                        @Override
                        public void run() {
                            timer2Text.setText("" + j);
                        }
                    });
                    Log.v("Thread2", String.valueOf(j));
                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread2.start();
    }

}
