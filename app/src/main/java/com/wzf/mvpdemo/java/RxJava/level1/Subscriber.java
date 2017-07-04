package com.wzf.mvpdemo.java.RxJava.level1;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 11:39
 * 订阅者 girl 有看电影的技能
 */

public abstract class Subscriber<T>{
    public abstract void onNext(T t);
}
