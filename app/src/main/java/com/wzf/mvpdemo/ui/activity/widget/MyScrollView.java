package com.wzf.mvpdemo.ui.activity.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-08-09 10:08
 */

public class MyScrollView extends ScrollView {
    private boolean disallowIntercept;
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 滑动冲突解决方法2从父控件出发
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(false);
        return super.onInterceptTouchEvent(ev);
//        return disallowIntercept;
    }

    public void myRequestDisallowInterceptTouchEvent(boolean disallowIntercept){
        this.disallowIntercept = disallowIntercept;
    }

    //    @Override
//    /**
//     * 重写该方法、达到使ListView适应ScrollView的效果
//     */
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }


}
