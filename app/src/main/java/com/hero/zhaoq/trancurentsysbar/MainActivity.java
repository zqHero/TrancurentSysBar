package com.hero.zhaoq.trancurentsysbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hero.zhaoq.trancurentsysbar.activitys.InBarActivity;
import com.hero.zhaoq.trancurentsysbar.activitys.TransparentActivity;

/**
 * 沉浸式  状态栏  Demo
 * zhaoqiang   zhaoq_hero@163.com
 *
 * 区分  变色状态栏    透明状态栏   以及沉浸式状态栏  解决  text问题现象
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = (Button) findViewById(R.id.transparentBar);
        btn3 = (Button) findViewById(R.id.inBar);


        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    //
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.transparentBar:
                //透明状态栏
                startActivity(new Intent(this,TransparentActivity.class));
                break;
            case R.id.inBar:
//                沉浸状态栏
                startActivity(new Intent(this,InBarActivity.class));
                break;
        }
    }
}
