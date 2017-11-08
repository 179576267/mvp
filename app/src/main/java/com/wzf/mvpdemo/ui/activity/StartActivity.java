package com.wzf.mvpdemo.ui.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.activity.banner.BannerActivity;
import com.wzf.mvpdemo.ui.activity.canvas.CanvasActivity;
import com.wzf.mvpdemo.ui.activity.canvas.ImageEditActivity;
import com.wzf.mvpdemo.ui.activity.canvas.ScratchCardActivity;
import com.wzf.mvpdemo.ui.activity.emoji.EmojiActivity;
import com.wzf.mvpdemo.ui.activity.listslide.ListSlideActivity;
import com.wzf.mvpdemo.ui.activity.login.LogInActivity;
import com.wzf.mvpdemo.ui.activity.photoview.PhotoViewActivity;
import com.wzf.mvpdemo.ui.activity.slide.FirstSlideActivity;
import com.wzf.mvpdemo.ui.activity.svg.SVGActivity;
import com.wzf.mvpdemo.ui.activity.waterwave.MyWaterWaveActivity;
import com.wzf.mvpdemo.ui.activity.widget.ScrollIncludeListActivity;
import com.wzf.mvpdemo.ui.activity.videorecord.VideoRecordActivity;
import com.wzf.mvpdemo.ui.base.BaseActivity;
import com.yixia.record.MediaRecorderActivity;

import java.util.ArrayList;

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
        addWindowView();
    }

    private void addWindowView() {
        final Button button = new Button(MyApplication.getAppInstance());
        button.setText("windowButton");
        final WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSLUCENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
        final WindowManager manager = (WindowManager) MyApplication.getAppInstance().getSystemService(WINDOW_SERVICE);
        manager.addView(button, mLayoutParams);
        button.setOnTouchListener(new View.OnTouchListener() {
            int mStartX;
            int mStartY;
            int mEndX;
            int mEndY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mEndX = mStartX = (int) event.getRawX();
                        mEndY = mStartY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndX = (int) event.getRawX();
                        mEndY = (int) event.getRawY();
                        if (needIntercept()) {
                            //getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
                            mLayoutParams.x = (int) event.getRawX() - button.getMeasuredWidth() / 2;
                            mLayoutParams.y = (int) event.getRawY() - button.getMeasuredHeight() / 2;
                            manager.updateViewLayout(button, mLayoutParams);
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (needIntercept()) {
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }

            /**
             * 是否拦截
             * @return true:拦截;false:不拦截.
             */
            private boolean needIntercept() {
                if (Math.abs(mStartX - mEndX) > 30 || Math.abs(mStartY - mEndY) > 30) {
                    return true;
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getAppInstance(), "点击悬浮窗", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick({R.id.btn_login, R.id.btn_CanvasActivity, R.id.ScratchCard, R.id.ImageEditActivity,
            R.id.VideoRecordActivity, R.id.slideActivity, R.id.MediaRecorderActivity, R.id.EmojiActivity, R.id.SVGActivity,
    R.id.ScrollIncludeListActivity, R.id.BannerActivity, R.id.ListSlideActivity, R.id.MyWaterWaveActivity, R.id.PhotoViewActivity})
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
            case R.id.MediaRecorderActivity:
                MediaRecorderActivity.startMethod(this);
                break;
            case R.id.EmojiActivity:
                startActivity(new Intent(this, EmojiActivity.class));
                break;
            case R.id.SVGActivity:
                startActivity(new Intent(this, SVGActivity.class));
                break;
            case R.id.ScrollIncludeListActivity:
                startActivity(new Intent(this, ScrollIncludeListActivity.class));
                break;
            case R.id.BannerActivity:
                startActivity(new Intent(this, BannerActivity.class));
                break;
            case R.id.ListSlideActivity:
                startActivity(new Intent(this, ListSlideActivity.class));
                break;
            case R.id.MyWaterWaveActivity:
                startActivity(new Intent(this, MyWaterWaveActivity.class));
                break;
            case R.id.PhotoViewActivity:
                ArrayList<String> urls = new ArrayList<>();
                urls.add("http://pic.jj20.com/up/allimg/1011/110GG01327/1G10G01327-1.jpg");
                urls.add("http://pic.jj20.com/up/allimg/1011/11051G30935/1G105130935-1.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i2/TB2wO3Et0BopuFjSZPcXXc9EpXa_!!409196487.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i3/TB2AGO0kxBmpuFjSZFsXXcXpFXa_!!1656188531.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i8/T2Q3oeXa0aXXXXXXXX_!!409196487.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i2/TB1DRvOQVXXXXX7XFXXXXXXXXXX_!!0-item_pic.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i4/TB1psr0QFXXXXbHXpXXXXXXXXXX_!!0-item_pic.jpg");
                PhotoViewActivity.startMethod(this, 2, urls);
                break;
            default:
                break;
        }
    }
}
