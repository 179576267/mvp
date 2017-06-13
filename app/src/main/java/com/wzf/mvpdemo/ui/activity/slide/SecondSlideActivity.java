package com.wzf.mvpdemo.ui.activity.slide;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;
import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.Model.User;
import com.wzf.mvpdemo.utils.eventbus.EventBus;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-05-03 10:25
 */

public class SecondSlideActivity extends AppCompatActivity {
    SlidrConfig mSlidrConfig;
    SlidrConfig.Builder mBuilder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_second);
        initSlide();
    }

    private void initSlide() {
        int primary = getResources().getColor(R.color.colorPrimary);
        int secondary = getResources().getColor(R.color.colorAccent);
        mBuilder = new SlidrConfig.Builder().primaryColor(primary)
                .secondaryColor(secondary)
                .scrimColor(Color.BLACK)
                .position(SlidrPosition.LEFT)
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(5f)
                .distanceThreshold(.35f);
        mSlidrConfig = mBuilder.build();
        Slidr.attach(this, mSlidrConfig);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post("SecondSlideActivity");
                        User user = new User("wangzhenfei", 20);
                        Log.i("EventBus", "send thread:-->>" + Thread.currentThread().getName());
                        EventBus.getDefault().post(user);
                    }
                }).start();

            }
        });
    }
}
