package com.wzf.mvpdemo.utils.imageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileDescriptor;

/**
 * Created by wangzhenfei on 2017/2/24.
 */

public class ImageResizer {
    private  final String TAG = this.getClass().getSimpleName();

    public ImageResizer() {
    }

    /**
     * 从资源加载图片
     * @param res
     * @param resId
     * @param reqWith
     * @param reqHeight
     * @return
     */
    public Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWith, int reqHeight){
        //第一步，只解析图片的边框
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 只解析边框
        BitmapFactory.decodeResource(res, resId, options);

        //计算缩放比例
        options.inSampleSize = calculateInSampleSize(options, reqWith, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }



    /**
     * 从文件描述符加载图片
     * @param fd
     * @param reqWith
     * @param reqHeight
     * @return
     */
    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fd, int reqWith, int reqHeight){
        //第一步，只解析图片的边框
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 只解析边框
        BitmapFactory.decodeFileDescriptor(fd,null,options);

        //计算缩放比例
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }


    /**
     * 计算缩放比例
     * @param options
     * @param reqWith
     * @param reqHeight
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWith, int reqHeight) {
        if(reqWith == 0 || reqHeight == 0){
            return 1;
        }
        // 图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if(height > reqHeight || width > reqWith){
            while ((height / inSampleSize >= reqHeight) && (width / inSampleSize >= reqWith)){
                inSampleSize *= 2;
            }
        }
        Log.i(TAG, "inSampleSize:" + inSampleSize);
        return inSampleSize;
    }
}
