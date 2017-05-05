package com.wzf.mvpdemo.ui.activity.bosspage;

import android.content.Intent;
import android.os.Bundle;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.base.BaseActivity;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-04-10 17:41
 */

public class BossPageActivity extends BaseActivity {
    BossPageFragment bossPageFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        bossPageFragment = new BossPageFragment();
        new BossPagePresenter(this,bossPageFragment);
        getSupportFragmentManager().beginTransaction().add(R.id.rm, bossPageFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bossPageFragment.onActivityResult(requestCode, resultCode, data);
    }
}
