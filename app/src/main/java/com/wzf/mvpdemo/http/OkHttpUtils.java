package com.wzf.mvpdemo.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.http.requestbody.ProgressRequestListener;
import com.wzf.mvpdemo.http.requestbody.ProgressResponseListener;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangzhenfei on 2017/2/28.
 */

public class OkHttpUtils {
    private static final int MAX_AGE = 2 * 24 * 60 * 60;//最大缓存时间
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 100M
    private static OkHttpUtils INSTANCE;
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit.Builder retrofit;
    private OkHttpClient.Builder client;



    public static OkHttpUtils getInstance(){
        if(INSTANCE == null){
            synchronized (OkHttpUtils.class){
                if(INSTANCE == null){
                    INSTANCE = new OkHttpUtils();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * 基于retrofit的机制，判断网络是否可用并提示，缓存的设置，添加t参数到request header中，避免t的改变影响缓存机制
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            // 缓存配置
            if (!isNetworkAvailable(MyApplication.getAppInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (!isNetworkAvailable(MyApplication.getAppInstance())) {
                response = response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + MAX_AGE)
                        .removeHeader("Pragma")
                        .build();
            } else {
                //有网的直接读取接口上@Headers的配置，实现某个请求的在线缓存
                String cacheControl = request.cacheControl().toString();
                response = response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };


    public OkHttpUtils() {
        File diskCacheDir = getDiskCacheDir(MyApplication.getAppInstance(), "okhttp");
        client = new OkHttpClient.Builder();
        client.cache(new Cache(diskCacheDir, DISK_CACHE_SIZE))
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(new LoggerInterceptor(null, true))
        .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)//离线缓存
        .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);//在线缓存
            retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create());
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .baseUrl(OrderUrlService.BASE_REQUEST_URL);
        }


    /**
     * 获取硬盘存储路径
     * @param mContext
     * @param path
     * @return
     */
    private File getDiskCacheDir(Context mContext, String path) {
        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        final String cachePath;
        if(externalStorageAvailable){
            File file  = mContext.getExternalCacheDir();
            cachePath = file.getPath();
        }else {
            cachePath = mContext.getCacheDir().getPath();
        }
        return  new File(cachePath + File.separator + path);
    }

    /**
     * 获取urlservice
     * @param t
     * @param <T>
     * @return
     */
    public <T> T getUrlSetvice(Class<T> t){
        return retrofit.client(client.build()).baseUrl(URL.BASE_REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(t);
    }
    /**
     * 创建带响应进度(下载进度)回调的service
     */
    public  <T> T createResponseService(Class<T> tClass, ProgressResponseListener listener){
        return retrofit
                .client(HttpClientHelper.addProgressResponseListener(new OkHttpClient.Builder() .
                        addInterceptor(new LoggerInterceptor(null, true)),listener))
                .baseUrl(URL.BASE_REQUEST_URL)
                .build()
                .create(tClass);
    }

    /**
     * 创建带请求体进度(上传进度)回调的service
     */
    public  <T> T createReqeustService(Class<T> tClass, ProgressRequestListener listener){
        return retrofit
                .client(HttpClientHelper.addProgressRequestListener(new OkHttpClient.Builder() .
                        addInterceptor(new LoggerInterceptor(null, true)),listener))
                .baseUrl(OrderUrlService.BASE_UPLOAD_URL)
                .build()
                .create(tClass);
    }


    /**
     * 当前是否联网
     *
     * @return
     */
    public static boolean isNetworkAvailable(Context pContext) {

        ConnectivityManager vConnectivityManager = (ConnectivityManager) pContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo vNetworkInfo = vConnectivityManager.getActiveNetworkInfo();
        // do nothing
        return vNetworkInfo != null && vNetworkInfo.isAvailable();
    }
}
