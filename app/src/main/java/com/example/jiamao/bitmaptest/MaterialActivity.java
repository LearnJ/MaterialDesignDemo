package com.example.jiamao.bitmaptest;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MaterialActivity extends AppCompatActivity {

    private FloatingActionButton fb;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        fb=findViewById(R.id.fab) ;
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialActivity.this, " 您点击了悬浮按钮 ", Toast.LENGTH_SHORT).show();

                Snackbar.make(v,"Data deleted",Snackbar.LENGTH_SHORT).setAction("撤销删除", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MaterialActivity.this,"撤销成功",Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        navigationView.setCheckedItem(R.id.nav_friends);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                navigationView.setCheckedItem(item.getItemId());
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu1,menu);
//        setIconEnable(menu,true);
//        menu.add(1,1,1,"item1").setIcon(R.drawable.icon);
//        menu.add(1,2,2,"item2").setIcon(R.drawable.icon);
//        menu.add(1,3,3,"item3").setIcon(R.drawable.icon);
//        //setIconEnable(menu,true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                Toast.makeText(this,"点击了",Toast.LENGTH_SHORT).show();
                mDrawerLayout.openDrawer(Gravity.START);
                break;
                default:
                    Toast.makeText(this,"点击了"+item.getItemId(),Toast.LENGTH_SHORT).show();
                    mDrawerLayout.openDrawer(Gravity.LEFT);
        }

        return true;
    }
}
