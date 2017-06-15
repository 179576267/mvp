package com.yixia.tools;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-05-05 18:13
 */

public class FileUtils {
    /**
     * 获取硬盘存储路径
     * @param mContext
     * @return
     */
    public static String getDiskCacheDir(Context mContext, String dirName) {
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if(externalStorageAvailable){
            if(mContext.getExternalCacheDir() != null){
                cachePath = mContext.getExternalCacheDir().getPath();
            }else {
                cachePath = mContext.getCacheDir().getPath();
            }
        }else {
            cachePath = mContext.getCacheDir().getPath();
        }
        return  cachePath + File.separator + dirName;
    }
}
