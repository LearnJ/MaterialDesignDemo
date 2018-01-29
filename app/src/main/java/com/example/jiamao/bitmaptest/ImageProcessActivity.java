package com.example.jiamao.bitmaptest;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.InputStream;
import java.lang.reflect.Method;

public class ImageProcessActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private final String TAG="ImageProcessActivity";
    private SeekBar r_seekBar;
    private SeekBar g_seekBar;
    private SeekBar b_seekBar;
    private SeekBar a_seekBar;
    private ImageView imageView;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_process);

        initView();
    }

    void initView(){
        imageView=findViewById(R.id.image);
        r_seekBar=findViewById(R.id.r_seekbar);
        g_seekBar=findViewById(R.id.g_seekbar);
        b_seekBar=findViewById(R.id.b_seekbar);
        a_seekBar=findViewById(R.id.a_seekbar);
        r_seekBar.setOnSeekBarChangeListener(this);
        g_seekBar.setOnSeekBarChangeListener(this);
        b_seekBar.setOnSeekBarChangeListener(this);
        a_seekBar.setOnSeekBarChangeListener(this);

        InputStream is = getResources().openRawResource(R.raw.test);
        bitmap = BitmapFactory.decodeStream(is);

        //bitmap= ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher_background)).getBitmap();
        Log.d(TAG, "bitmap=: "+bitmap);
    }

    /**
     * 把drawable》Bitmap
     * @param drawable
     */
    private void drawableToBitamp(Drawable drawable)
    {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
    }

    void handleBitmapPixels(Bitmap b){

        int width=b.getWidth();
        int height=b.getHeight();
        int [] oldp=new int[width*height];
        int [] newp=new int[width*height];
        b.getPixels(oldp,0,width,0,0,width,height);

        for (int i=0;i<width*height;i++){

            int color=oldp[i];
            Color.red(color);
            Color.green(color);
            Color.blue(color);
            Color.alpha(color);

            newp[i]=Color.argb(0,255,1,0);

        }

    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        ColorMatrix cm=new ColorMatrix();

        switch (seekBar.getId()){

            case R.id.r_seekbar:
                cm.setRotate(0,progress);
                break;

            case R.id.g_seekbar:
                cm.setRotate(1,progress);
                break;

            case R.id.b_seekbar:
                cm.setRotate(2,progress);
                break;

            case R.id.a_seekbar:
                cm.setSaturation(progress);
                break;
        }

        //Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.bitmap1);
       // Bitmap bitmap=((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
        Bitmap b=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);


        Paint paint=new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        Canvas mCanvas=new Canvas(b);

        mCanvas.drawBitmap(bitmap,0,0,paint);
        imageView.setImageBitmap(b);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        Log.d(TAG, "onStartTrackingTouch: ");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        Log.d(TAG, "onStopTrackingTouch: ");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       //getMenuInflater().inflate(R.menu.menu1,menu);
        setIconEnable(menu,true);
       menu.add(1,1,1,"item1").setIcon(R.drawable.icon);
        menu.add(1,2,2,"item2").setIcon(R.drawable.icon);
        menu.add(1,3,3,"item3").setIcon(R.drawable.icon);
       //setIconEnable(menu,true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
