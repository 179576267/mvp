package com.wzf.mvpdemo.ui.activity.bosspage;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.http.model.response.BossPageResponseDto;
import com.wzf.mvpdemo.ui.activity.canvas.CanvasActivity;
import com.wzf.mvpdemo.ui.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 17:42
 */

public class BossPageFragment extends BaseFragment implements BossPageContract.View{
    public static final int REQUEST_WITH_DRAW = 0X457;
    private BossPageContract.Presenter presenter;
    @Bind(R.id.iv_menu_data)
    ImageView ivMenuData;
    @Bind(R.id.tv_total)
    TextView tvTotal;
    @Bind(R.id.tv_extracting)
    TextView tvExtracting;
    @Bind(R.id.ll_sub_logout)
    RelativeLayout llSubLogout;
    private View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = bActivity.getLayoutInflater().inflate(
                    R.layout.fragment_boss_page, null);
            ButterKnife.bind(this, mRootView);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBossPageData();
    }

    @Override
    public void showToast(String msg) {
        bActivity.showToast(msg);
    }

    @Override
    public void showPageData(BossPageResponseDto dto) {
        if(dto != null){
            tvExtracting.setText("提现中:" + dto.getExtractingMoney() + "元");
            tvTotal.setText(String.valueOf(dto.getTotalMoney()));
        }
    }

    @Override
    public void setPresenter(BossPageContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @OnClick({R.id.iv_menu_data, R.id.tv_account_detial, R.id.ll_sub_logout, R.id.ll_charge, R.id.ll_extract,
            R.id.tv_extracting, R.id.tv_pay_pass, R.id.tv_logout, R.id.ll_data_analysis})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_menu_data:
                llSubLogout.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_pay_pass:
//                new SetPayPasswordDialog(bActivity).show();
                llSubLogout.setVisibility(View.GONE);
                break;
            case R.id.tv_account_detial:
//                bActivity.startActivity(new Intent(bActivity, IncomeAndExpensesActivity.class));
                break;
            case R.id.ll_charge:
                break;
            case R.id.ll_extract:
//                bActivity.startActivityForResult(new Intent(bActivity, WithDrawActivity.class), REQUEST_WITH_DRAW);
                break;
            case R.id.tv_extracting:
//                bActivity.startActivity(new Intent(bActivity, BillDetailActivity.class));
                break;
            case R.id.ll_data_analysis:
//                if(URL.DEBUG){
                    bActivity.startActivity(new Intent(bActivity, CanvasActivity.class));
                    llSubLogout.setVisibility(View.GONE);
//                }
                break;
            case R.id.tv_logout:
//                logout();
                llSubLogout.setVisibility(View.GONE);
                break;
            case R.id.ll_sub_logout:
                llSubLogout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
