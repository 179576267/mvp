package com.wzf.mvpdemo.ui.activity.design_patterns.factory_pattern.factory;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-10-14 16:22
 */

public class Factory {
    /**
     * 简单工厂模式
     * @param type
     * @return
     */
    public static Api create(int type){
        Api api = null;
        switch (type){
            case 1:
                api = new ApiImpl1();
                break;
            case 2:
                api = new ApiImpl2();
                break;
            case 3:
                api = new ApiImpl3();
                break;
        }
        return api;
    }

    /**
     * 反射模式
     * @param clz
     * @return
     */
    public static <T extends Api> T create(Class<T> clz){
        Api api = null;
        try {
            api = (Api) clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T)api;
    }
}
