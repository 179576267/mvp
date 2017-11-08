package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.factory;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-10-14 16:15
 * class非公开，禁止外部实例化
 */

class ApiImpl2 implements Api {
    @Override
    public void function() {
        System.out.println("ApiImpl2");
    }
}
