package com.wzf.mvpdemo.ui.activity.slide;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.Model.User;
import com.wzf.mvpdemo.ui.widget.AdImagePagerAdapter;
import com.wzf.mvpdemo.ui.widget.BannerView;
import com.wzf.mvpdemo.ui.widget.BannerViewPager;
import com.wzf.mvpdemo.ui.widget.PageIndicatorView;
import com.wzf.mvpdemo.utils.eventbus.EventBus;
import com.wzf.mvpdemo.utils.eventbus.Subscribe;
import com.wzf.mvpdemo.utils.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-05-03 10:25
 */

public class FirstSlideActivity extends AppCompatActivity {
//    @Bind(R.id.view_pager)
//    BannerViewPager viewPager;
//    @Bind(R.id.pageview)
//    PageIndicatorView pageview;
    @Bind(R.id.banner_view)
    BannerView bannerView;
    private List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_first);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstSlideActivity.this, SecondSlideActivity.class));
            }
        });
        bannerShow();
    }

    private void bannerShow() {
        images.add("http://p1.pstatp.com/large/26e500058ff0be1bd190");
        images.add("http://p9.pstatp.com/large/26e500059249a53316a4");
        images.add("http://p1.pstatp.com/large/26e900059ada61eb1046");
        images.add("http://p1.pstatp.com/large/26e80002ef6279affb8f");
        images.add("http://p3.pstatp.com/origin/216f002051f861dcbf85");
//        viewPager.setAdapter(new AdImagePagerAdapter(this, images) {
//            @Override
//            public void pageClick(int position) {
//                Toast.makeText(MyApplication.getAppInstance(), position + "", Toast.LENGTH_SHORT).show();
//            }
//        });
//        pageview.setTotalPage(images.size());
//        pageview.setType(1);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            public void onPageScrollStateChanged(int arg0) {
//            }
//
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//
//            public void onPageSelected(int arg0) {
//                pageview.setCurrentPage(arg0 % images.size());
//            }
//        });
//        viewPager.setInterval(3000);
//        viewPager.startAutoScroll();
//        int times = Integer.MAX_VALUE / images.size();
//        viewPager.setCurrentItem(times / 2 * images.size());
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        bannerView.setBannerAdapter(new AdImagePagerAdapter(this, images) {
            @Override
            public void pageClick(int position) {
                Toast.makeText(MyApplication.getAppInstance(), position + "", Toast.LENGTH_SHORT).show();
            }
        }, images.size());
        bannerView.startAutoSctoll(3000);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void reciver(User user) {
        Log.i("EventBus", "reciver thread:-->>" + Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        bannerView.destory();
        EventBus.getDefault().unRegister(this);
        super.onDestroy();
    }
}
