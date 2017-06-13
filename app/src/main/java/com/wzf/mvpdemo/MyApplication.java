package com.wzf.mvpdemo;

import android.os.Build;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.yixia.camera.VCamera;
import com.yixia.camera.util.DeviceUtils;
import com.yixia.tools.FileUtils;

import java.io.File;


/**
 * Created by zhenfei.wang on 2016/8/8.
 */
public class MyApplication extends MultiDexApplication {
    private final String TAG = getClass().getSimpleName();
    private static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        initVideoCaChe();
        application = this;
    }

    /**
     * 初始化视频缓冲路径
     */
    private void initVideoCaChe() {
        // 设置拍摄视频缓存路径
        VCamera.setVideoCachePath(FileUtils.getDiskCacheDir(this, "video"));
        // 开启log输出,ffmpeg输出到logcat
        VCamera.setDebugMode(false);
        VCamera.initialize(this);
        // 初始化拍摄SDK，必须
        String cpu = Build.CPU_ABI;
//        for (String s : CPU_UNSUPPORT_ARRAY) {
//            if (s.equals(cpu)) {
//                vCameraSupport = false;
//            }
//        }
//
//        if (vCameraSupport) {
//            VCamera.initialize(this);
//        }
    }


    public  static  MyApplication getAppInstance(){
        return application;
    }
}
