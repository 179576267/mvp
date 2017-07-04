package com.wzf.mvpdemo.java.RxJava.level1;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 11:50
 *  被观察者 boy 有个动作，请看电影
 *  T 看电影
 *  Subscriber<? super T> 看电影类型的女生
 */

public interface OnSubscribe<T> extends Action1<Subscriber<? super T>>{

}
