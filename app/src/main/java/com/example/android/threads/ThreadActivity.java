package com.example.android.threads;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ThreadActivity extends AppCompatActivity{

    Handler handler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage(Message inputMessage){
            System.out.println("handleMessage "+inputMessage.toString());
        }

    };

    private class MyHandlerThread extends HandlerThread {

        private int counter;
        private boolean cancelled = false;
        Activity activity;
        Handler handler;
        Looper looper;

        public MyHandlerThread(String name, Activity activity){
            super(name);
            this.activity = activity;
        }

        public void run(){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("doWOrk");
                    while (!cancelled){
                        TextView textView = (TextView) findViewById(R.id.thread_counter);
                        textView.setText(String.valueOf(counter));
//                        textView.post(runnable);
                        counter++;
                    }
                }
            });
            this.quit();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);
    }


}
