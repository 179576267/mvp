package com.wzf.mvpdemo.http;


import com.wzf.mvpdemo.http.requestbody.ProgressRequestBody;
import com.wzf.mvpdemo.http.requestbody.ProgressRequestListener;
import com.wzf.mvpdemo.http.requestbody.ProgressResponseBody;
import com.wzf.mvpdemo.http.requestbody.ProgressResponseListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-03-02 14:52
 */
public class HttpClientHelper {
    /**
     * 包装OkHttpClient，用于下载文件的回调
     * @param progressListener 进度回调接口
     * @return 包装后的OkHttpClient
     */
    public static OkHttpClient addProgressResponseListener(OkHttpClient.Builder client ,final ProgressResponseListener progressListener){
        if(client == null) {
            client = new OkHttpClient.Builder();
        }
        //增加拦截器
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截
                Response originalResponse = chain.proceed(chain.request());

                //包装响应体并返回
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                        .build();
            }
        });
        return client.build();
    }


    /**
     * 包装OkHttpClient，用于上传文件的回调
     * @param progressListener 进度回调接口
     * @return 包装后的OkHttpClient
     */
    public static OkHttpClient addProgressRequestListener(OkHttpClient.Builder client, final ProgressRequestListener progressListener){
        if(client == null) {
            client = new OkHttpClient.Builder();
        }
        //增加拦截器
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .method(original.method(), new ProgressRequestBody(original.body(),progressListener))
                        .build();
                return chain.proceed(request);
            }
        });
        return client.build();
    }
}
