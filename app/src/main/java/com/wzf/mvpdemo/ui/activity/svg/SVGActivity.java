package com.wzf.mvpdemo.ui.activity.svg;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.activity.svg.utils.SVGUtil;
import com.wzf.mvpdemo.ui.base.BaseActivity;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-15 10:28
 */

public class SVGActivity extends BaseActivity {
    @Bind(R.id.im)
    ImageView im;
    int id[] = { R.raw.shape_5, R.raw.shape_circle_2, R.raw.shape_flower,
            R.raw.shape_heart, R.raw.shape_star, R.raw.shape_star_2,
            R.raw.shape_star_3 };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.change)
    public void onClick() {
        int num = new Random().nextInt(id.length);
        Log.e("chane", "num=" + num);
        changeImage(id[num]);
    }
    public void changeImage(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.fire);
        Bitmap result = SVGUtil.getResouceBitmap(this, id, bitmap);
        bitmap.recycle();
        im.setImageBitmap(result);
    }

}
