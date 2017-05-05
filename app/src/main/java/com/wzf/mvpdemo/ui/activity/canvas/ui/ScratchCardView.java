package com.wzf.mvpdemo.ui.activity.canvas.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wzf.mvpdemo.R;


/**
 * Created by fengkuang on 17/4/12.
 */

public class ScratchCardView extends View {
    private Context mContext;
    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;

    private Paint mPaint;

    private Bitmap downBitmap;
    private Bitmap upBitmap;
    private Canvas mCanvas;
    private Path mPath;

    public ScratchCardView(Context context) {
        this(context,null);
    }

    public ScratchCardView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(0);
        mPaint.setStrokeWidth(50);
        downBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_123);

        upBitmap = Bitmap.createBitmap(downBitmap.getWidth(),downBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(upBitmap);
        mCanvas.drawColor(Color.GRAY);
        mPath = new Path();
    }


    /**
     * 计算控件的高度和宽度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 设置宽度
         */
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mWidth = specSize;
        }
//        else if (specMode == MeasureSpec.AT_MOST)// wrap_content
//        {
//            // 由图片决定的宽
//            int desireByImg = getPaddingLeft() + getPaddingRight()
//                    + mSrc.getWidth();
//            mWidth = Math.min(desireByImg, specSize);
//        }

        /***
         * 设置高度
         */

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
        {
            mHeight = specSize;
        }
//        else if (specMode == MeasureSpec.AT_MOST)// wrap_content
//        {
//            int desire = getPaddingTop() + getPaddingBottom()
//                    + mSrc.getHeight();
//            mHeight = Math.min(desire, specSize);
//        }
        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(downBitmap,0,0,null);
        canvas.drawBitmap(upBitmap,0,0,null);
    }
}
