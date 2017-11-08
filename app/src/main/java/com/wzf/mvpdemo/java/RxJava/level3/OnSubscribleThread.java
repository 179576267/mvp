package com.wzf.mvpdemo.java.RxJava.level3;

import com.wzf.mvpdemo.java.RxJava.Observable;
import com.wzf.mvpdemo.java.RxJava.level1.OnSubscribe;
import com.wzf.mvpdemo.java.RxJava.level1.Subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 15:39
 */

public class OnSubscribleThread<T>  implements OnSubscribe<T>{
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    Observable<T> source;

    public OnSubscribleThread(Observable<T> source) {
        this.source = source;
    }

    public Observable<T> getSource() {
        return source;
    }

    public void setSource(Observable<T> source) {
        this.source = source;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
//        System.out.println("OnSubscribleThread call :" + Thread.currentThread().getName());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                System.out.println("OnSubscribleThread run :" + Thread.currentThread().getName());
                source.subsercibe(subscriber);
            }
        };
//        new Thread(runnable).start();
        executorService.execute(runnable);
    }
}
