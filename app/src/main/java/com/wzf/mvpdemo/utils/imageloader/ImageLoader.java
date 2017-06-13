package com.wzf.mvpdemo.utils.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;


import com.wzf.mvpdemo.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangzhenfei on 2017/2/24.
 */

public class ImageLoader {
    private static final String TAG = ImageLoader.class.getSimpleName();
    public static final int MESSAGE_POST_RESULT = 1;
    private static final int TAG_KEY_URL = R.id.btn;
    private LruCache<String, Bitmap> mMemoryCache;
    private DiskLruCache mDiskLruCache;
    private Context mContext;
    private ImageResizer mImageResizer;
    private static final int DISK_CACHE_INDEX = 0;
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 500;//500M
    private boolean mIsDiskLruCacheCreated = false;
    private static final int IO_BUFFER_SIZE = 8 * 1024;

    //开启一个线程池
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "ImageLoader" + mCount.getAndDecrement());
        }
    };
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            10L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<Runnable>(),
            sThreadFactory);

    private Handler mMainHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            LoaderResult result = (LoaderResult) msg.obj;
            ImageView imageView = result.imageView;
            imageView.setImageBitmap(result.bitmap);
            String url = (String) imageView.getTag(TAG_KEY_URL);
            if(url.equals(result.url)){
                imageView.setImageBitmap(result.bitmap);
            }else {
                Log.i(TAG, "set image bitmap,but uri has changed");
            }

        }
    };

    private ImageLoader(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        mImageResizer = new ImageResizer();
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024); // 转化为kb
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };

        File diskCacheDir = getDiskCacheDir(mContext, "bitmap");
        if(!diskCacheDir.exists()){
            diskCacheDir.mkdirs();
        }
        if(getUsableSpace(diskCacheDir) > DISK_CACHE_INDEX){
            try {
                mDiskLruCache = DiskLruCache.open(diskCacheDir, 1, 1, DISK_CACHE_SIZE);
                mIsDiskLruCacheCreated = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ImageLoader build(Context context){
        return new ImageLoader(context);
    }

    /**
     * 添加一张图片到缓存
     * @param key
     * @param bitmap
     */
    private void addBitmapToMemoryCache(String key, Bitmap bitmap){
        if(getBitmapFromMemoryCache(key) == null){
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     * 将bitmap加载到ImageView中
     */
    public void bindBitmap(final String url, final ImageView imageView, final int reqWidth, final int reqHeight){
        imageView.setTag(TAG_KEY_URL, url);
        Bitmap bitmap = getBitmapFromMemoryCache(url);
        if(bitmap != null){ // 内存有图片
            imageView.setImageBitmap(bitmap);
            return;
        }

        // 内测没有图片，就要去磁盘或者网络获取
        Runnable loadBitmapTask = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = loadBitmap(url, reqWidth, reqHeight);
                if(bitmap != null){
                    LoaderResult result = new LoaderResult(imageView, url, bitmap);
                    mMainHandler.obtainMessage(MESSAGE_POST_RESULT, result).sendToTarget();
                }
            }
        };
        THREAD_POOL_EXECUTOR.execute(loadBitmapTask);
    }

    /**
     * 异步从硬盘或者网络加载bitmap
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap loadBitmap(String url, int reqWidth, int reqHeight) {
        Bitmap bitmap = getBitmapFromMemoryCache(url); // 先从内测获取
        if(bitmap != null){
            Log.i(TAG, "loadBitmapFromMemoryCache,url:" + url);
            return bitmap;
        }
        try {
            bitmap = loadBitmapFromDiskCache(url, reqWidth, reqHeight); // 再从硬盘寻找
            if(bitmap != null){
                Log.i(TAG, "loadBitmapFromDiskCache,url:" + url);
                return bitmap;
            }
            bitmap = loadBitmapFromHttp(url, reqWidth, reqHeight);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(bitmap == null && !mIsDiskLruCacheCreated){
            Log.i(TAG, "DiskLurCache id not Created");
            bitmap = downloadBitmapFromUrl(url);
        }

        return bitmap;
    }

    /**
     * 从网络加载图片到硬盘,并且读取出来
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws Exception
     */
    private Bitmap loadBitmapFromHttp(String url, int reqWidth, int reqHeight)
            throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new RuntimeException("can not visit network from UI Thread.");
        }
        if (mDiskLruCache == null) {
            return null;
        }
        Log.d(TAG, "loadBitmapFromHttp,url:" + url);
        String key = hashKeyFromUrl(url);
        DiskLruCache.Editor editor = mDiskLruCache.edit(key);
        if (editor != null) {
            OutputStream outputStream = editor.newOutputStream(DISK_CACHE_INDEX);
            if (downloadUrlToStream(url, outputStream)) {
                editor.commit();
            } else {
                editor.abort();
            }
            mDiskLruCache.flush();
        }
        return loadBitmapFromDiskCache(url, reqWidth, reqHeight);
    }

    /**
     * 将url转化为流
     * @param urlString
     * @param outputStream
     * @return
     */
    public boolean downloadUrlToStream(String urlString,
                                       OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(),
                    IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, IO_BUFFER_SIZE);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (IOException e) {
            Log.e(TAG, "downloadBitmap failed." + e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           if(in != null){
               try {
                   in.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
        return false;
    }

    /**
     * 从网络加载图片
     * @param urlString
     * @return
     */
    private Bitmap downloadBitmapFromUrl(String urlString) {
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        BufferedInputStream in = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), IO_BUFFER_SIZE);
            bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    /**
     * 从硬盘拿图片资源
     * @param url
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap loadBitmapFromDiskCache(String url, int reqWidth, int reqHeight) throws Exception {
        if(Looper.myLooper() == Looper.getMainLooper()){
            Log.i(TAG, "load bitmap from ui thread, it is not recommended");
        }
        if(mDiskLruCache == null){
            return  null;
        }
        Bitmap bitmap = null;
        String key = hashKeyFromUrl(url);
        DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
        if(snapshot != null){
            FileInputStream fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
            FileDescriptor fileDescriptor = fileInputStream.getFD();
            bitmap = mImageResizer.decodeSampleBitmapFromFileDescriptor(fileDescriptor, reqWidth, reqHeight);
            if(bitmap != null){
                addBitmapToMemoryCache(key, bitmap);
            }
        }
        return bitmap;
    }


    /**
     * 从内存拿一张图片
     * @param url
     * @return
     */
    private Bitmap getBitmapFromMemoryCache(String url) {
        String key = hashKeyFromUrl(url);
        return mMemoryCache.get(key);
    }




    /*************************************************工具类************************************************/
    /**
     * 获取指定路径的可用空间大小
     * @param diskCacheDir
     * @return
     */
    private long getUsableSpace(File diskCacheDir) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            return diskCacheDir.getUsableSpace();
        }
        final StatFs statFs = new StatFs(diskCacheDir.getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    /**
     * 获取硬盘存储路径
     * @param mContext
     * @param bitmap
     * @return
     */
    private File getDiskCacheDir(Context mContext, String bitmap) {
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
        return  new File(cachePath + File.separator +bitmap);
    }

    /**
     * 对url进行MD5编码
     * @param url
     * @return
     */
    private String hashKeyFromUrl(String url) {
        String cacheKey = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(url.getBytes());
            cacheKey = bytesToHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return cacheKey;
    }

    private String bytesToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < digest.length; i++){
            String hex = Integer.toHexString(0xFF & digest[i]);
            if(hex.length() == 1){
                sb.append("0");
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * bitmap加载结果
     */
    private static class LoaderResult{
        public ImageView imageView;
        public String url;
        public Bitmap bitmap;

        public LoaderResult(ImageView imageView, String url, Bitmap bitmap) {
            this.imageView = imageView;
            this.url = url;
            this.bitmap = bitmap;
        }
    }
}
