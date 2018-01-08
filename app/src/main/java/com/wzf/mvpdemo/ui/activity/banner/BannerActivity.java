package com.wzf.mvpdemo.ui.activity.banner;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-13 11:05
 */

public class BannerActivity extends BaseActivity {
    @Bind(R.id.banner)
    BannerView banner;
    public String[] titles = new String[]{
            "湖人12月19日退役科比球衣 8号24号同时升空",
            "“老司机”带你飞 这些球员来到小牛后迈向成功",
            "巴恩斯违法为为范文芳",
//            "一种篮球，两种打发",
//            "一种篮球，两种打发",
    };

    public String[] images = new String[]{
            "http://inews.gtimg.com/newsapp_ls/0/2037911204/0",
            "http://inews.gtimg.com/newsapp_ls/0/2037923767/0",
            "http://inews.gtimg.com/newsapp_ls/0/2037689373/0",
//            "http://inews.gtimg.com/newsapp_ls/0/2021071327/0",
//            "http://inews.gtimg.com/newsapp_ls/0/2021071327/0",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        final List<BannerView.BannerInfo> infos = new ArrayList<>();
        for(int i = 0; i < images.length; i++){
            infos.add(new BannerView.BannerInfo(titles[i], images[i]));
        }
        banner.setBannerInfos(infos, new BannerView.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                showToast(infos.get(position).title);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
