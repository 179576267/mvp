package com.wzf.mvpdemo.ui.activity.login;

import com.wzf.mvpdemo.ui.base.BasePresenter;
import com.wzf.mvpdemo.ui.base.BaseView;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 15:47
 */

public interface LoginContract {

    interface View extends BaseView<Presenter>{
        void showToast(String msg);
    }

    interface Presenter extends BasePresenter{
        void login(String account, String psw);
    }
}
