package com.wzf.mvpdemo.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by zhenfei.wang on 2016/9/9.
 */
public class BaseFragment extends Fragment {
    protected BaseActivity bActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bActivity = (BaseActivity) context;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            onPause();
        }else {
            onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(bActivity == null){
            bActivity = (BaseActivity) getActivity();
        }
    }
}
