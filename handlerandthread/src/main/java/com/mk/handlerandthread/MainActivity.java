package com.mk.handlerandthread;

import android.app.ProgressDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final int UPLOAD_START = 1;
    private static final int UPLOAD_PROGRESS = 2;
    private static final int UPLOAD_FINISH = 3;
    private static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new MyHandler(this);
        new MYThread().start();
    }

    private static class MyHandler extends Handler {
        ProgressDialog dialog;
        //Using a weak reference means you won't prevent garbage collection
        WeakReference<MainActivity> myClassWeakReference;
        MyHandler(MainActivity myClassInstance) {
            myClassWeakReference = new WeakReference<>(myClassInstance);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLOAD_START:
                    // Set up a progress dialog when upload starts
                    Toast.makeText(myClassWeakReference.get(), "Progress start", Toast.LENGTH_SHORT).show();
                    dialog = new ProgressDialog(myClassWeakReference.get());
                    dialog.setMessage(myClassWeakReference.get().getString(R.string.uploading));
                    dialog.setCancelable(false);
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setMax(msg.arg1);
                    dialog.show();
                    break;
                case UPLOAD_PROGRESS:
                    // Update progress
                    dialog.setProgress(msg.arg1);
                    break;
                case UPLOAD_FINISH:
                    Toast.makeText(myClassWeakReference.get(), "Finished", Toast.LENGTH_SHORT).show();
                    // When upload finishes, dismiss progress dialog
                    View mainContainer = myClassWeakReference.get().findViewById(R.id.text);
                    GradientDrawable bgDrawable = (GradientDrawable) mainContainer.getBackground();
                    bgDrawable.setColor(ContextCompat.getColor(myClassWeakReference.get(), R.color.colorPrimary));
                    dialog.dismiss();
                    break;
                default:
                    break;
            }
        }


    }

    private class MYThread extends Thread {

        /*MYThread(Uri selectedImage) {
            this.selectedImage = selectedImage;
        }*/

        @Override
        public void run() {
            Message msg;

            // Notify the main thread that the upload starts
            msg = handler.obtainMessage(UPLOAD_START, 0, 0);
            handler.sendMessage(msg);

            for (int i = 0; i < 10; i++) {
                try {
                    msg = handler.obtainMessage(UPLOAD_PROGRESS, i, i);
                    handler.sendMessage(msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Upload finishes
            msg = handler.obtainMessage(UPLOAD_FINISH);
            handler.sendMessage(msg);
        }
    }

}

