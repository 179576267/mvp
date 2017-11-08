package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.factory;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-10-14 16:15
 * class非公开，禁止外部实例化，最小支持原则
 */

class ApiImpl1 implements Api {
    @Override
    public void function() {
        System.out.println("ApiImpl1");
    }
}
