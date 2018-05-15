package com.example.android.threads;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AsyncTaskActivity asyncTaskActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAsyncActivity(View view){
        if (asyncTaskActivity == null){
            Intent intent = new Intent(this, AsyncTaskActivity.class);
            startActivity(intent);
        }
    }


}
