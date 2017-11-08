package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.abstract_factory;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-10-17 16:39
 * 抽象工厂：构建和逻辑分离
 */

public class Client {
    public static void main(String[] args) {
        new AndroidFactory().create().show();
        new IosFactory().create().show();
    }
}
