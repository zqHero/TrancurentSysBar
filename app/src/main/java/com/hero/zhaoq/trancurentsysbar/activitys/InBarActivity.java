package com.hero.zhaoq.trancurentsysbar.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hero.zhaoq.trancurentsysbar.R;

import java.lang.reflect.Field;

public class InBarActivity extends AppCompatActivity {

    private RelativeLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_bar);

        toolbar = (RelativeLayout) findViewById(R.id.toolbar);
    }

    boolean isshow = true;

    //    Android 4.4中才会生效，对应的Android版本兼容的判断请自行处理。
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                if(isshow){
                    hideSystemUI(getWindow().getDecorView());
                    //添加   监听系统ui监听事件
                    toolbar.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            toolbar.setPadding(toolbar.getPaddingLeft(), 0,toolbar.getPaddingRight(), toolbar.getPaddingBottom());
                        }
                    });
                    isshow = false;
                }else{  //没有显示   状态栏
                    showSystemUI(getWindow().getDecorView());//DecorView为整个Window界面的最顶层View。
                    //添加   监听系统ui监听事件
//                    toolbar.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
//                        @Override
//                        public void onSystemUiVisibilityChange(int visibility) {
//                            toolbar.setPadding(toolbar.getPaddingLeft(), getStatusBarHeight(InBarActivity.this),toolbar.getPaddingRight(), toolbar.getPaddingBottom());
//                        }
//                    });
                    isshow = true;
                }
                break;
            default:
                break;
        }
        return true;
    }

//    Android 4.4中才会生效，对应的Android版本兼容的判断请自行处理。
    @SuppressLint("NewApi")
    public static void hideSystemUI(View view) {
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @SuppressLint("NewApi")
    public static void showSystemUI(View view) {
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 获状态栏高度
     * 通过  反射获取
     * @param context 上下文
     * @return 通知栏高度
     */
    public  int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int temp = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
