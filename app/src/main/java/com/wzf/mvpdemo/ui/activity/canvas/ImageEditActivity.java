package com.wzf.mvpdemo.ui.activity.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.base.BaseActivity;

public class ImageEditActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener{
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;

    private ImageView imageView;

    private float mHue;
    private float mSaturation;
    private float mLum;

    private int MID_VALUE = 50;
    private Bitmap src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        imageView = (ImageView) findViewById(R.id.imageView);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekBar1:
                mHue = (progress - MID_VALUE) *1.0f / MID_VALUE *180;
                break;
            case R.id.seekBar2:
                mSaturation = progress *1.0f / MID_VALUE;
                break;
            case R.id.seekBar3:
                mLum = progress *1.0f / MID_VALUE;
                break;
        }

        imageView.setImageBitmap(getBitmap());
    }

    private Bitmap getBitmap() {
        if(src == null){
            src = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_123);
        }
        Bitmap bmp = Bitmap.createBitmap(src.getWidth(), src.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint mPaint = new Paint();

        ColorMatrix matrix1 = new ColorMatrix();
        matrix1.setRotate(0, mHue);
        matrix1.setRotate(1, mHue);
        matrix1.setRotate(2, mHue);


        ColorMatrix matrix2 = new ColorMatrix();
        matrix2.setSaturation(mSaturation);

        ColorMatrix matrix3 = new ColorMatrix();
        matrix3.setScale(mLum,mLum, mLum,1);

        ColorMatrix matrixSet = new ColorMatrix();
        matrixSet.postConcat(matrix1);
        matrixSet.postConcat(matrix2);
        matrixSet.postConcat(matrix3);
        mPaint.setColorFilter(new ColorMatrixColorFilter(matrixSet));
        canvas.drawBitmap(src,0,0,mPaint);
        return bmp;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
