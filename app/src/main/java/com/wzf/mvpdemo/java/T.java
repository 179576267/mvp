package com.wzf.mvpdemo.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 15:35
 */

public class T {
    public static class Fruit{}
    public static class Apple extends Fruit{}
    public static class Banana extends Fruit{}


    public static void main(String[] args) {
        //? extends Fruit 用于返回参数的限定，不能用于参数类型的限定
        List<? extends Fruit> fruits1 = new ArrayList<>();
        //fruits1.add(new Fruit());//编译不通过
        //fruits1.add(new Apple());//编译不通过
        Fruit fruit1 = fruits1.get(0);//编译通过

        //? super Fruit 用于参数类型的限定，不能用于返回参数限定
        List<? super Fruit> fruits2 = new ArrayList<>();
        fruits2.add(new Fruit());//编译通过
        fruits2.add(new Apple());//编译通过
        //Fruit f = fruits2.get(0);//编译不通过

    }
}
