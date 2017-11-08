package com.wzf.mvpdemo.ui.activity.listslide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-26 09:44
 */

public class ItemSlideView extends RelativeLayout {
    private View contentView;
    private View menuView;

    private int contentWidth;
    private int menuWidth;
    private int height;

    int touchSlop;
    private Scroller scroller;

    public ItemSlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 当资源文件加载完毕的时候调用
     * 可以在这里获取一些资源文件
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menuView = getChildAt(1);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentWidth = contentView.getMeasuredWidth();
        menuWidth = menuView.getMeasuredWidth();
        height = getMeasuredHeight();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        menuView.layout(contentWidth, 0, contentWidth + menuWidth, height);
    }

    private float startX;
    private float startY;
    private float downX;//只赋值一次
    private float downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //1.按下记录坐标
                downX = startX = event.getX();
                downY = startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //2.记录结束值
                float endX = event.getX();
                float endY = event.getY();
                //3.计算偏移量
                float distanceX = endX - startX;
                int toScrollX = (int) (getScrollX() - distanceX);

                if(toScrollX < 0){
                    toScrollX = 0;
                }else if(toScrollX > menuWidth){
                    toScrollX = menuWidth;
                }
                scrollTo(toScrollX, 0);


                //3.计算偏移量
                float distanceY = endY - startY;
                int toScrollY = (int) (getScrollY() - distanceY);
                if(toScrollY < touchSlop && toScrollY < toScrollX){ // 反拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                int totalX = getScrollX();
                if(totalX > menuWidth / 2){
                    openMenu();
                }else {
                    closeMenu();
                }
                break;
        }
        return true;
    }

    public void closeMenu() {
       int distance = 0 - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distance, getScrollY());
        invalidate();
        if(onStatusChangeListener != null){
            onStatusChangeListener.onClose(this);
        }
    }

    private void openMenu() {
        int distance = menuWidth - getScrollX();
        scroller.startScroll(getScrollX(), getScrollY(), distance, getScrollY());
        invalidate();
        if(onStatusChangeListener != null){
            onStatusChangeListener.onOpen(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    private float interceptX;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        super.onInterceptTouchEvent(event);
        boolean isIntercept = false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                interceptX = event.getX();
                if(onStatusChangeListener != null){
                    onStatusChangeListener.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX = getX() - interceptX;
                if(Math.abs(distanceX) > touchSlop){
                    isIntercept = true;
                }
                break;
        }
        return isIntercept;
    }

    public interface OnStatusChangeListener{
        void onOpen(ItemSlideView view);
        void onDown(ItemSlideView view);
        void onClose(ItemSlideView view);
    }

    private OnStatusChangeListener onStatusChangeListener;

    public void setOnStatusChangeListener(OnStatusChangeListener onStatusChangeListener) {
        this.onStatusChangeListener = onStatusChangeListener;
    }
}
