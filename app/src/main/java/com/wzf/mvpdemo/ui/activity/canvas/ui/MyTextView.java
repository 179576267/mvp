package com.wzf.mvpdemo.ui.activity.canvas.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-08-10 18:00
 */

public class MyTextView extends View {
    private int h = 400;
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(10000, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(80);
//        canvas.drawText("王振飞abcdefghijk", 100, h / 2, mPaint);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float baselineY = h / 2 + (fontMetrics.bottom-fontMetrics.top)/2 - fontMetrics.bottom;
        canvas.drawText("王振飞abcdefghijk", 100, baselineY, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, h/2, 1000, h/2,mPaint);
    }
}
