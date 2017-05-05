package com.wzf.mvpdemo.http;

import android.app.Activity;
import android.content.Context;


import com.wzf.mvpdemo.ui.dialog.NetRequestWaitDialog;
import com.wzf.mvpdemo.utils.DebugLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wangzhenfei on 2017/2/28.
 */

public class OnOkHttpDataCallBack implements Callback<ResponseBody> {
    public static final int PARAS_DATA_ERROR = 2222;
    public static final int SERVER_ERROR = 2220;
    public static final int UNAUTHORIZED = 7777;
    public static final int INSUFFICIENT_BALANCE = 2001; // 余额不足
    public static final int SUCCESS = 0; // 成功
    private Class t;
    private WeakReference<Context> contextWeakReference;
    private NetRequestWaitDialog dialog;
    private boolean showLoadingDialog = true;

    public OnOkHttpDataCallBack(Context context, Class t) {
        this.t = t;
        contextWeakReference = new WeakReference<Context>(context);
    }

    public OnOkHttpDataCallBack(Context context) {
        this.t = String.class;
        contextWeakReference = new WeakReference<Context>(context);
    }

    public OnOkHttpDataCallBack(Activity activity, boolean showDialog) {
        this.t = String.class;
        contextWeakReference = new WeakReference<Context>(activity);
        this.showLoadingDialog = showDialog;
        try {
            if(showDialog){
                if(contextWeakReference.get() != null){
                    dialog = new NetRequestWaitDialog(contextWeakReference.get(), "数据加载中...");
                    dialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public OnOkHttpDataCallBack(Activity activity, Class t, boolean showDialog) {
        this.t = t;
        contextWeakReference = new WeakReference<Context>(activity);
        this.showLoadingDialog = showDialog;
        try {
            if(showDialog){
                dialog = new NetRequestWaitDialog(activity, "数据加载中...");
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        dismissDialog();
        if (response.isSuccessful()) {
            try {
                String payload = response.body().string();
                /**
                 * 一般来说，服务器返回的响应码都包含 code，msg，data 三部分，在此根据自己的业务需要完成相应的逻辑判断
                 * 以下只是一个示例，具体业务具体实现
                 */
                JSONObject jsonObject = new JSONObject(payload);
                final String msg = jsonObject.optString("message", "");
                int code = jsonObject.optInt("code", 0);
                String data = jsonObject.optString("data", "");
                if(code == SUCCESS){
                    try {
                        onSuccess(data);
                    } catch (Exception e) {
                        DebugLog.e("OKHTTP", "成功后的数据错误：" + e.toString());
                        e.printStackTrace();
                    }
                }else {
                    try {
                        onError(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLog.e("OKHTTP","错误后的数据错误："  + e.toString());
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                try {
                    onError(e.toString());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }

    }

    private void dismissDialog(){
        if(dialog !=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    final public void onFailure(Call<ResponseBody> call, Throwable t) {
        dismissDialog();
        DebugLog.e("OKHTTP", t.toString());
        try {
            onError(t.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onSuccess(String json) throws Exception {}

    public void onError(String error) throws Exception {
        DebugLog.e("OKHTTP", error);
    }

}
