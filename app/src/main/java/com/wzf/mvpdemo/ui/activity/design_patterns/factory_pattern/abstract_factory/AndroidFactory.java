package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.abstract_factory;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-10-17 16:38
 */

public class AndroidFactory implements IFactory {
    @Override
    public IApi create() {
        return new AndroidApi();
    }
}
