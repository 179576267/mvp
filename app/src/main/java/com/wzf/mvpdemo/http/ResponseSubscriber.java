package com.wzf.mvpdemo.http;

import android.app.Activity;
import android.content.Context;

import com.wzf.mvpdemo.http.model.response.BaseResponse;
import com.wzf.mvpdemo.ui.dialog.NetRequestWaitDialog;

import java.lang.ref.WeakReference;

import rx.Subscriber;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-17 17:06
 */

public class ResponseSubscriber<T> extends Subscriber<BaseResponse<T>> {
    public static int NET_OR_SERVER_ERROR = 0X4562;
    private  NetRequestWaitDialog dialog;
    private WeakReference<Activity> contextWeakReference;
    private boolean showDialog;

    public ResponseSubscriber() {
    }

    public ResponseSubscriber(Activity activity, boolean showDialog) {
        contextWeakReference = new WeakReference<Activity>(activity);
        this.showDialog = showDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(showDialog && contextWeakReference.get() != null){
            dialog = new NetRequestWaitDialog(contextWeakReference.get(), "数据加载中...");
            dialog.show();
        }
    }

    @Override
    public void onCompleted() {
        if(dialog != null){
            dialog.dismiss();
        }
        contextWeakReference = null;
    }

    @Override
    public final void onError(Throwable e) {
        onFailure(NET_OR_SERVER_ERROR, e.toString());
    }

    @Override
    public final void onNext(BaseResponse<T> t) {
        if(t != null){
            if(t.getCode() == 0){
                onSuccess(t.getData());
            }else {
                onFailure(t.getCode(), t.getMessage());
            }
        }
    }

    public void onSuccess(T t){};
    public void onFailure(int code, String message){};
}
