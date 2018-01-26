package com.example.jiamao.bitmaptest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private Button goSurfaceView,goMaterialActivity,goWebViewActivity,goImageProcessActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        Timer timer=new Timer();
//        TimerTask task=new TimerTask() {
//            @Override
//            public void run() {
//                //Intent intent=new Intent(MainActivity.this,MaterialActivity.class);
//                Intent intent=new Intent(MainActivity.this,WebViewActivity.class);
//                startActivity(intent);
//            }
//        };
//
//        timer.schedule(task,1000);

    }

    void initView(){

        goImageProcessActivity=findViewById(R.id.go_ImageProcessSample);
        goMaterialActivity=findViewById(R.id.go_MaterialActivity);
        goSurfaceView=findViewById(R.id.go_SurfaceViewSample);
        goWebViewActivity=findViewById(R.id.go_webview);

        goWebViewActivity.setOnClickListener(this);
        goImageProcessActivity.setOnClickListener(this);
        goSurfaceView.setOnClickListener(this);
        goMaterialActivity.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Intent intent=null;
        switch (v.getId()){

            case R.id.go_ImageProcessSample:

                intent=new Intent(MainActivity.this,ImageProcessActivity.class);
                break;

            case R.id.go_MaterialActivity:

                intent=new Intent(MainActivity.this,MaterialActivity.class);
                break;

            case R.id.go_SurfaceViewSample:
                intent=new Intent(MainActivity.this,SurfaceViewActivity.class);
                break;

            case R.id.go_webview:

                intent=new Intent(MainActivity.this,WebViewActivity.class);
                break;

        }
        if (intent!=null){
            startActivity(intent);
        }

    }
}
