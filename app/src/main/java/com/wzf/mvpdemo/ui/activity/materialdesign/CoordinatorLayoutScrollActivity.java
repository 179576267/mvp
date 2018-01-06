package com.wzf.mvpdemo.ui.activity.materialdesign;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.base.BaseActivity;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-11-30 17:38
 * 上滑隐藏导航条
 * Coordinator:协调员
 */

public class CoordinatorLayoutScrollActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_coordinator);

//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//        /**
//         * 决定左上角的图标是否可以点击。没有向左的小图标。 true 图标可以点击  false 不可以点击。
//         */
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_coordinator2);
    }
}
