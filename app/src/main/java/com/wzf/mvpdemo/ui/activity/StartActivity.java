package com.wzf.mvpdemo.ui.activity;

import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.ui.activity.banner.BannerActivity;
import com.wzf.mvpdemo.ui.activity.canvas.CanvasMenuActivity;
import com.wzf.mvpdemo.ui.activity.emoji.EmojiActivity;
import com.wzf.mvpdemo.ui.activity.listslide.ListSlideActivity;
import com.wzf.mvpdemo.ui.activity.materialdesign.MaterialMenuActivity;
import com.wzf.mvpdemo.ui.activity.pay.AliPayActivity;
import com.wzf.mvpdemo.ui.activity.photoview.PhotoViewActivity;
import com.wzf.mvpdemo.ui.activity.svg.SVGActivity;
import com.wzf.mvpdemo.ui.activity.waterwave.MyWaterWaveActivity;
import com.wzf.mvpdemo.ui.activity.widget.ScrollIncludeListActivity;
import com.wzf.mvpdemo.ui.base.BaseMenuActivity;
import com.yixia.record.MediaRecorderActivity;

import java.util.ArrayList;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-13 15:41
 */

public class StartActivity extends BaseMenuActivity {
    @Override
    public void init() {
        addChild("悬浮窗", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWindowView();
            }
        });
        addChild("自定义控件", CanvasMenuActivity.class);
        addChild("Emoji表情", EmojiActivity.class);
        addChild("SVG测试", SVGActivity.class);
        addChild("小视频录制", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaRecorderActivity.startMethod(StartActivity.this);
            }
        });
        addChild("图片预览", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> urls = new ArrayList<>();
                urls.add("http://pic.jj20.com/up/allimg/1011/110GG01327/1G10G01327-1.jpg");
                urls.add("http://pic.jj20.com/up/allimg/1011/11051G30935/1G105130935-1.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i2/TB2wO3Et0BopuFjSZPcXXc9EpXa_!!409196487.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i3/TB2AGO0kxBmpuFjSZFsXXcXpFXa_!!1656188531.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i8/T2Q3oeXa0aXXXXXXXX_!!409196487.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i2/TB1DRvOQVXXXXX7XFXXXXXXXXXX_!!0-item_pic.jpg");
                urls.add("http://img.alicdn.com/bao/uploaded/i4/TB1psr0QFXXXXbHXpXXXXXXXXXX_!!0-item_pic.jpg");
                PhotoViewActivity.startMethod(StartActivity.this, 2, urls);
            }
        });

        addChild("Material设计", MaterialMenuActivity.class);
        addChild("支付", AliPayActivity.class);

    }


    private void addWindowView() {
        final Button button = new Button(MyApplication.getAppInstance());
        button.setText("悬浮窗");
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
}
