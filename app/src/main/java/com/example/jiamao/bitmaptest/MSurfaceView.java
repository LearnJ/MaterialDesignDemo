package com.example.jiamao.bitmaptest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.FrameStats;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by jiamao on 2018/1/25.
 */

public class MSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder surfaceHolder;
    private Canvas mCanvas;
    private boolean shouldDrawing=true;
    Paint mPaint;
    Path mPath;
    public MSurfaceView(Context context) {
        super(context);
        surfaceHolder=getHolder();
        setKeepScreenOn(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        surfaceHolder.addCallback(this);
        mPaint=new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath=new Path();

    }

    public MSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        shouldDrawing=true;
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        while(shouldDrawing){
                draw();
        }
    }

    private void draw(){

        long start=System.currentTimeMillis();
        try {

        mCanvas=surfaceHolder.lockCanvas();
        mCanvas.drawColor(Color.RED);
        mCanvas.drawCircle(400,200,100,mPaint);

        mPaint.setTextSize(100);
        mCanvas.drawText(""+System.currentTimeMillis(),100,100,mPaint);

        float [] lines={0,500,0,1000,100,500,100,1000};
        mCanvas.drawLines(lines,mPaint);

        mCanvas.drawPath(mPath,mPaint);
        }catch(Exception e){


        }finally {
            if (mCanvas!=null)
               surfaceHolder.unlockCanvasAndPost(mCanvas);
            long end=System.currentTimeMillis();
            if (end-start<100){
                try {
                    Thread.sleep(100-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                break;

            case  MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_UP:

                mPath.reset();
                break;
        }
        return true;
    }
}
