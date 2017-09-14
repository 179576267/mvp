package com.wzf.mvpdemo.ui.activity.canvas;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wzf.mvpdemo.R;


/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-03-20 11:35
 */

public class CanvasActivity extends Activity {
    private SurfaceHolder holder;
    private Paint paintArea;
    private Paint paintLine;
    private Paint paintText;
    final int HEIGHT = 300;
    final int WIDTH = 300;
    private final int radius = WIDTH/2 - 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        final SurfaceView surface = (SurfaceView)findViewById(R.id.show);
        //初始化SurfaceHolder对象
        holder = surface.getHolder();
        holder.setFixedSize(WIDTH, HEIGHT);
        paintArea = new Paint();
        paintArea.setColor(0xFFDBEEF3);
        paintArea.setAntiAlias(true);


        paintLine = new Paint();
        paintLine.setColor(0XFF43A8C3);
        paintLine.setStrokeWidth(2);

        paintText = new Paint();
        paintText.setColor(Color.BLUE);
        paintText.setTextSize(20);
        paintArea.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);

        Button sin =(Button)findViewById(R.id.sin);
        Button cos =(Button)findViewById(R.id.cos);
        OnClickListener listener = (new OnClickListener() {
            @Override
            public void onClick(final View source) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        drawBack(holder);
                        drawClock();
//                        drawCircle();
                    }
                }).start();

            }
        });



        sin.setOnClickListener(listener);
        cos.setOnClickListener(listener);
        holder.addCallback(new Callback() {
            public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){
                drawBack(holder);
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // TODO Auto-generated method stub
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub
            }

        });


    }

    private void drawCircle() {
        Canvas canvas = holder.lockCanvas(new Rect(0,0,WIDTH,HEIGHT));
        canvas.drawColor(Color.WHITE);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        canvas.drawCircle(150, 150, 100, mPaint);

        canvas.saveLayerAlpha(0, 0 ,400, 400, 255, Canvas.ALL_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200 ,100, mPaint);
        holder.unlockCanvasAndPost(canvas);
    }

    private void drawClock() {
        Canvas canvas = holder.lockCanvas(new Rect(0,0,WIDTH,HEIGHT));
        //画外圆
        Paint paintCircle = new Paint();
        paintCircle.setColor(Color.WHITE);
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        canvas.drawCircle(WIDTH / 2, HEIGHT/2, radius, paintCircle );

        //画刻度
        Paint paintDegree = new Paint();
        paintDegree.setColor(Color.WHITE);
        for(int i = 0; i < 12; i++){
            if(i % 3 == 0){
                paintDegree.setTextSize(30);
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextAlign(Paint.Align.CENTER);
                canvas.drawLine(getX(0), getY(radius), getX(0), getY(radius - 30), paintDegree);
                String degreeText = String.valueOf(i);
                canvas.drawText(degreeText, getX(0),getY(radius - 60), paintDegree);
            }else {
                paintDegree.setTextSize(20);
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextAlign(Paint.Align.CENTER);
                canvas.drawLine(getX(0), getY(radius), getX(0), getY(radius - 20), paintDegree);
                String degreeText = String.valueOf(i);
                canvas.drawText(degreeText, getX(0),getY(radius - 50), paintDegree);
            }
            canvas.rotate(30, WIDTH/2, HEIGHT/2);
        }

        //画指正
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        paintHour.setColor(Color.WHITE);
        Paint paintMin = new Paint();
        paintMin.setStrokeWidth(10);
        paintMin.setColor(Color.WHITE);

        canvas.save();
        canvas.rotate(30, WIDTH/2, HEIGHT/2);
        canvas.drawLine(getX(0), getY(0), getX(150), getY(0), paintHour);
        canvas.save();
//                        canvas.rotate(30, WIDTH/2, HEIGHT/2);
        canvas.drawLine(getX(0), getY(0), getX(0), getY(100), paintMin);
        canvas.restore();
        holder.unlockCanvasAndPost(canvas);
    }


    private float getX(float srcX){
        return WIDTH / 2 + srcX;
    }

    private float getY(float srcY){
        return HEIGHT / 2 - srcY;
    }

    private void drawBack(SurfaceHolder holder){
        Canvas canvas = holder.lockCanvas(new Rect(0,0,WIDTH,HEIGHT));
        //绘制白色背景
        canvas.drawColor(Color.WHITE);
        //绘制坐标轴
        // canvas.drawLine(getX(0), getX(0), PADDING_X, LENG_X , p);//Y轴
        holder.unlockCanvasAndPost(canvas);

    }

}
