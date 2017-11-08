package com.wzf.mvpdemo.java.RxJava;

import com.wzf.mvpdemo.java.RxJava.level1.OnSubscribe;
import com.wzf.mvpdemo.java.RxJava.level1.Subscriber;
import com.wzf.mvpdemo.java.RxJava.level2.Fun1;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-07-04 11:37
 */

public class Main {
    public static class User{
        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
//        Observable.create(new OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                System.out.println("call");
//                subscriber.onNext("我是女生，我看电影");
//            }
//        }).subsercibe(new Subscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//        });
        //string 做饭，User看电影
        Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getName());
                System.out.println("call");
                subscriber.onNext("我是女生，我看电影");
            }
        }).subscriOnIo().map(new Fun1<String, User>() {
            @Override
            public User transform(String s) {
                System.out.println(Thread.currentThread().getName());
                return new User(s);
            }
        }).subscriOnIo().subsercibe(new Subscriber<User>() {
            @Override
            public void onNext(User user) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(user);
            }
        });
    }
}
