package com.example.jiamao.bitmaptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MSurfaceView(this));


        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                //Intent intent=new Intent(MainActivity.this,MaterialActivity.class);
                Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        };

        timer.schedule(task,1000);

    }
}
