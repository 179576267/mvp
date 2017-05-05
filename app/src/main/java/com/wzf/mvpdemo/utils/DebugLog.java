package com.wzf.mvpdemo.utils;

import android.util.Log;
import android.widget.Toast;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.http.URL;

/**
 * zhenfei.wang
 * 调试打印信息
 */

public class DebugLog {

    public static void v(String tag, String msg) {
        if (URL.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (URL.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (URL.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (URL.DEBUG) {
            Log.i("DEBUG", msg);
        }
    }

    public static void w(String tag, String msg) {
        if (URL.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (URL.DEBUG) {
            Log.e(tag, msg);
        }
    }

    /**
     * 在开发阶段的测试toast
     * @param s
     */
    public static void toast(String s){
        if(URL.DEBUG){
            Toast.makeText(MyApplication.getAppInstance().getApplicationContext(),
                    s,
                    Toast.LENGTH_LONG).show();
        }
    }

}
