package com.wzf.mvpdemo.ui.activity.canvas.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.wzf.mvpdemo.R;


/**
 * Created by fengkuang on 17/4/12.
 */

public class ShadowView extends View {
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

    private Bitmap topBitmap;
    private Bitmap bottomBitmap;

    public ShadowView(Context context) {
        this(context,null);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        topBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.bg_123);
        topBitmap = Bitmap.createBitmap(topBitmap,0,topBitmap.getHeight()/4,topBitmap.getWidth(),(int)(topBitmap.getHeight() * 2.0f/4));
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);
        bottomBitmap = Bitmap.createBitmap(topBitmap, 0, 0 ,topBitmap.getWidth(), topBitmap.getHeight(), matrix, true);

        LinearGradient gradient = new LinearGradient(0f, topBitmap.getHeight() * 1.0f, 0f, topBitmap.getHeight() * 1.25f, 0xdd000000, 0x10000000, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(topBitmap, 0, 0, null);
        canvas.drawBitmap(bottomBitmap,0, topBitmap.getHeight(), null);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        canvas.drawRect(0, topBitmap.getHeight(), topBitmap.getWidth(), topBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }
}
