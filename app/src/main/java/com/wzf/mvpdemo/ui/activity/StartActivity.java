package com.wzf.mvpdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.activity.canvas.CanvasActivity;
import com.wzf.mvpdemo.ui.activity.canvas.ImageEditActivity;
import com.wzf.mvpdemo.ui.activity.canvas.ScratchCardActivity;
import com.wzf.mvpdemo.ui.activity.login.LogInActivity;
import com.wzf.mvpdemo.ui.activity.slide.FirstSlideActivity;
import com.wzf.mvpdemo.ui.activity.videorecord.VideoRecordActivity;
import com.wzf.mvpdemo.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-13 15:41
 */

public class StartActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_CanvasActivity, R.id.ScratchCard, R.id.ImageEditActivity,
            R.id.VideoRecordActivity, R.id.slideActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this, LogInActivity.class));
                break;
            case R.id.btn_CanvasActivity:
                startActivity(new Intent(this, CanvasActivity.class));
                break;
            case R.id.ScratchCard:
                startActivity(new Intent(this, ScratchCardActivity.class));
                break;
            case R.id.ImageEditActivity:
                startActivity(new Intent(this, ImageEditActivity.class));
                break;
            case R.id.VideoRecordActivity:
                startActivity(new Intent(this, VideoRecordActivity.class));
                break;
            case R.id.slideActivity:
                startActivity(new Intent(this, FirstSlideActivity.class));
                break;
        }
    }
}