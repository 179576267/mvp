package com.wzf.mvpdemo.ui.activity.materialdesign;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-12-02 18:41
 */

public class MoveView extends TextView {

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                setPosition((int) event.getRawX(), (int) event.getRawY());
                    return true;
            case MotionEvent.ACTION_UP:
                    return true;
            default:
                break;
        }
        return false;
    }

    private void setPosition(int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        setLayoutParams(layoutParams);
    }


}
