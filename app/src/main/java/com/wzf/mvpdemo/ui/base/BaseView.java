package com.wzf.mvpdemo.ui.base;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 15:37
 */

public interface BaseView <T extends BasePresenter>{

    void setPresenter(T presenter);
}
