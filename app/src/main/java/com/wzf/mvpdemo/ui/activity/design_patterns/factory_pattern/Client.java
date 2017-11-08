package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern;

import com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.factory.Api;
import com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.factory.Factory;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-10-14 16:15
 */

public class Client {
    public static void main(String[] args) {
        /**
         * 工厂模式
         * 核心思想
         *  提供创建对象的功能，并且不需要关心具体的实现，
         *  降低调用者和模块的耦合度（最少知识原则）
         */
        //一般情况接口实现，需要知道实现类,由于实现类是非public所以调用者无法实例化
//        Api obj = new ApiImpl1();
        //工厂模式, 不需要知道具体的实现，接口隔离
        Api obj1 = Factory.create(1);
        obj1.function();

        /**
         * 工厂方法
         */
    }
}
