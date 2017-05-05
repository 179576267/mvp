package com.wzf.mvpdemo.ui.activity.slide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wzf.mvpdemo.R;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-05-03 10:25
 */

public class FirstSlideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_first);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstSlideActivity.this, SecondSlideActivity.class));
            }
        });
    }
}
