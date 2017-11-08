package com.wzf.mvpdemo.ui.activity.widget;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.utils.DebugLog;
import com.yixia.tools.ScreenUtils;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-08-10 09:21
 */

public class SlideMenuLayout extends HorizontalScrollView {
    private int screenWidth = ScreenUtils.getScreenWidth(MyApplication.getAppInstance());
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mTouchSlop;
    int scrollX;
    private int SCROLL_MAX;

    public SlideMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        //系统允许的滑动的最小距离
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout parent = (LinearLayout) getChildAt(0);
        mMenu = (ViewGroup) parent.getChildAt(0);
        mContent = (ViewGroup) parent.getChildAt(1);
        int menuPaddingLeft = screenWidth / 4;
        SCROLL_MAX = screenWidth - menuPaddingLeft;
        mMenu.getLayoutParams().width = SCROLL_MAX;
        mContent.getLayoutParams().width = screenWidth;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollX = SCROLL_MAX;
        scrollTo(scrollX, 0);

    }

    private float moveX;
    private float lastMoveX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                lastMoveX = downX;
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = ev.getRawX();
                float xDiff = moveX - downX;
                lastMoveX = moveX;

                if (Math.abs(xDiff) > mTouchSlop) {
                    if(xDiff < 0 && scrollX == SCROLL_MAX){//边界检测,在最右边向左滑动
                        return false;
                    }else if(xDiff > 0 && scrollX == 0){//边界检测,在最左边向右滑动
                        return false;
                    }else {
                        return true;
                    }

                }
                break;

            default:
                break;
        }
        return false;
    }

    private float downX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                DebugLog.i("downX:  " + downX);
                break;
            case MotionEvent.ACTION_UP:
                float offX = ev.getX() - downX;
                DebugLog.i("offX: " + offX);
                if (offX < 0) {//<---
                    if (Math.abs(offX) > screenWidth / 3) { // 滑动距离大，展示内容
                        scrollX = SCROLL_MAX;
                        this.smoothScrollTo(SCROLL_MAX, 0);
                    } else {
                        this.smoothScrollTo(scrollX, 0);
                    }
                } else {//-->
                    if (offX > screenWidth / 3) { // 滑动距离大，展示主侧边栏
                        scrollX = 0;
                        this.smoothScrollTo(0, 0);
                    } else {
                        this.smoothScrollTo(scrollX, 0);
                    }
                }
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //滑动百分比因子
        float factor = (float) (l * 1.0f / SCROLL_MAX);
//        DebugLog.i("滑动百分比因子: " + factor);
        //1.平移效果(兼容2.x，nineOldeAndroid.jar)
        ViewHelper.setTranslationX(mMenu, SCROLL_MAX * factor * 0.6f);
        //2.缩放效果
        float leftScale = 1 - 0.2f * factor;
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);

//        float rightScale = 0.8f + 0.2f * factor;
//        ViewHelper.setScaleX(mContent, rightScale);
//        ViewHelper.setScaleY(mContent, rightScale);
        //3.透明度效果
        ViewHelper.setAlpha(mMenu, 1 - factor);
    }
}
