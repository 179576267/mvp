package com.wzf.mvpdemo.java.RxJava;

import com.wzf.mvpdemo.java.RxJava.level1.OnSubscribe;
import com.wzf.mvpdemo.java.RxJava.level1.Subscriber;
import com.wzf.mvpdemo.java.RxJava.level2.Fun1;
import com.wzf.mvpdemo.java.RxJava.level2.OnSubscriberLift;
import com.wzf.mvpdemo.java.RxJava.level3.OnSubscribleThread;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 11:38
 * 事件场所 house
 * T 当前女士类型 看电影的类型
 */

public class Observable <T>{
    private OnSubscribe<T> onSubscribe;

    public Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public void subsercibe(Subscriber<? super T> subscriber){
        onSubscribe.call(subscriber);
    }

    public static <T> Observable <T> create(OnSubscribe<T> onSubscribe){
        return new Observable<T>(onSubscribe);
    }

    public <R> Observable<R> map(Fun1<? super T, ? extends R> fun){
        return new Observable<R>(new OnSubscriberLift<T, R>(onSubscribe, fun));
    }

    public Observable<T> subscriOnIo(){
        return create( new OnSubscribleThread<T>(this));
    }
}
