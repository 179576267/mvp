package com.wzf.mvpdemo.ui.activity.bosspage;

import android.content.Intent;

import com.wzf.mvpdemo.http.model.response.BossPageResponseDto;
import com.wzf.mvpdemo.ui.base.BasePresenter;
import com.wzf.mvpdemo.ui.base.BaseView;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 17:47
 */

public class BossPageContract {
    interface View extends BaseView<BossPageContract.Presenter> {
        void showToast(String msg);
        void showPageData(BossPageResponseDto dto);
    }

    interface Presenter extends BasePresenter {

        void getBossPageData();
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
