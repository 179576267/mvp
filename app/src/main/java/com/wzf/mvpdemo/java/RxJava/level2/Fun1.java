package com.wzf.mvpdemo.java.RxJava.level2;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 14:20
 * 类型转换器c
 */

public interface Fun1<T, R> {
    R transform (T t);
}
