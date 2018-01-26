package com.example.jiamao.bitmaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SurfaceViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MSurfaceView(this));
    }
}
