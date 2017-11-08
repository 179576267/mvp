package com.wzf.mvpdemo.ui.activity.widget;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.wzf.mvpdemo.MyApplication;
import com.yixia.tools.ScreenUtils;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-08-10 11:43
 */

public class QQSlideItemLayout extends LinearLayout {
    private int screenWidth = ScreenUtils.getScreenWidth(MyApplication.getAppInstance());
    private int mTouchSlop;
    private ViewGroup mFirstGroup;
    private ViewGroup mSecondGroup;
    private Scroller mScroller;
    private int SCROLL_MAX;
    public QQSlideItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        //系统允许的滑动的最小距离
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        LinearLayout parent = (LinearLayout) getChildAt(0);
        mFirstGroup = (ViewGroup) getChildAt(0);
        mSecondGroup = (ViewGroup) getChildAt(1);
        SCROLL_MAX = screenWidth / 4;
        mFirstGroup.getLayoutParams().width = screenWidth;
        mSecondGroup.getLayoutParams().width = SCROLL_MAX;
    }

    float downX;
    float lastMoveX;
    float moveX;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                lastMoveX = downX;
                requestParentDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = ev.getRawX();
                float xDiff = Math.abs(moveX - downX);
                lastMoveX = moveX;
                if (xDiff > mTouchSlop){
                    return true;
                }
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private float startX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float offX = event.getX() - startX;
                scrollTo((int)event.getX(), 0);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        final ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }


    /**
     * 在滑动的过程中会被不断调用
     */
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
    }
}
