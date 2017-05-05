package com.wzf.mvpdemo.ui.activity.bosspage;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.wzf.mvpdemo.http.OkHttpUtils;
import com.wzf.mvpdemo.http.OnOkHttpDataCallBack;
import com.wzf.mvpdemo.http.OrderUrlService;
import com.wzf.mvpdemo.http.model.request.PostParam;
import com.wzf.mvpdemo.http.model.response.BossPageResponseDto;
import com.wzf.mvpdemo.utils.JsonUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 17:50
 */

public class BossPagePresenter implements BossPageContract.Presenter {
    private final  BossPageContract.View mBossView;
    private Activity mActivity;

    public BossPagePresenter(@NonNull Activity activity, @NonNull BossPageContract.View bossView){
        this.mBossView = checkNotNull(bossView, "tasksView cannot be null!");
        this.mActivity = checkNotNull(activity);
        mBossView.setPresenter(this);
    }

    @Override
    public void getBossPageData() {
        OrderUrlService service = OkHttpUtils.getInstance().getUrlSetvice(OrderUrlService.class);
        service.getBossPageArg(new PostParam()).enqueue(new OnOkHttpDataCallBack(mActivity) {
            @Override
            public void onSuccess(String json) throws Exception {
                BossPageResponseDto responseDto = JsonUtils.fromJSON(BossPageResponseDto.class, json);
                if (responseDto != null) {
                    mBossView.showPageData(responseDto);
                }
            }

            @Override
            public void onError(String error) throws Exception {
                super.onError(error);
                mBossView.showToast(error);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == BossPageFragment.REQUEST_WITH_DRAW) {
            getBossPageData();
        }
    }
}
