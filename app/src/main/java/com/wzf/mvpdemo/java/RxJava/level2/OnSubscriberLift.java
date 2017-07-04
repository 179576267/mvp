package com.wzf.mvpdemo.java.RxJava.level2;

import com.wzf.mvpdemo.java.RxJava.level1.OnSubscribe;
import com.wzf.mvpdemo.java.RxJava.level1.Subscriber;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 14:30
 * T 那个想女生看电影的男生
 * R 那个能看电影的女生
 * boy-->找OnSubscriberLift要一个会看电影的女生-->OnSubscriberLift只有一个会做饭的女生SubscriberLift
 * -->OnSubscriberLift 只能要会做饭的 SubscriberLift 去陪boy看电影
 * -->SubscriberLift自己不会看电影，又找到自己会看电影的朋友R，让他代替自己去陪boy看电影
 */

public class OnSubscriberLift <T, R> implements OnSubscribe<R> {
    OnSubscribe<T> boy;
    Fun1<? super T, ? extends R> fun1;

    public OnSubscriberLift(OnSubscribe<T> boy, Fun1<? super T, ? extends R> fun1) {
        this.boy = boy;
        this.fun1 = fun1;
    }

    /**
     * 想看电影的女生
     * @param subscriber
     */
    @Override
    public void call(Subscriber<? super R> subscriber) {
        Subscriber<? super T> wifi = new SubscriberLift<>(fun1, subscriber);
        boy.call(wifi);
    }

    public class SubscriberLift<T, R> extends Subscriber<T>{
        Fun1<? super T, ? extends R> fun1;
        Subscriber<? super R> girl;

        public SubscriberLift(Fun1<T, R> fun1, Subscriber<? super R> girl) {
            this.fun1 = fun1;
            this.girl = girl;
        }

        @Override
        public void onNext(T t) {
            R r = fun1.transform(t);
            girl.onNext(r);
        }
    }
}
