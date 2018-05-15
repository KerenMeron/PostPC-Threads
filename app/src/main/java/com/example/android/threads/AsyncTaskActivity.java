package com.example.android.threads;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity{
    private AsyncTaskWorker asyncTaskWorker;
    private int counter;

    private class AsyncTaskWorker extends AsyncTask<Void, String, Void> {

        @Override
        public Void doInBackground(Void... nullInputs) {
            for (counter = 0; counter < 10; counter++){
                publishProgress(String.valueOf(counter));
                if (isCancelled()){
                    return null;
                }
                SystemClock.sleep(500);
            }
            publishProgress("Done!");
            return null;
        }

        @Override
        public void onProgressUpdate(String... counterVals){
            TextView textView = (TextView) findViewById(R.id.async_counter);
            textView.setText(counterVals[0]);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        System.out.println("async task oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
    }

    public void createAsyncWorker(View view){
        if (asyncTaskWorker == null){
            asyncTaskWorker = new AsyncTaskWorker();
        }
    }

    public void startAsyncWorker(View view){
        if (asyncTaskWorker != null){
            asyncTaskWorker.execute();
        }
    }
    public void cancelAsyncWorker(View view){
        if (asyncTaskWorker != null){
            asyncTaskWorker.cancel(true);
        }
    }
}