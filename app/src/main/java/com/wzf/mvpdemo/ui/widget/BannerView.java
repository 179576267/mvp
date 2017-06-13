package com.wzf.mvpdemo.ui.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.utils.ScreenUtils;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-13 15:33
 */

public class BannerView extends RelativeLayout {
    /**
     * 上下文.
     */
    private Context context;
    private BannerViewPager bannerViewPager;
    private PageIndicatorView indicatorView;
    private int totalSize;

    /**
     * 创建一个AbSlidingPlayView.
     *
     * @param context the context
     */
    public BannerView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * 从xml初始化的AbSlidingPlayView.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public void initView(Context context) {
        this.context = context;
        bannerViewPager = new BannerViewPager(context);
        bannerViewPager.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        addView(bannerViewPager);

        indicatorView = new PageIndicatorView(context);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        lp1.setMargins(0, 0 ,0 , ScreenUtils.dip2px(MyApplication.getAppInstance(), 10));
        addView(indicatorView, lp1);
    }

    public void setBannerAdapter(PagerAdapter adapter, final int totalSize){
        this.totalSize = totalSize;
        bannerViewPager.setAdapter(adapter);
        indicatorView.setTotalPage(totalSize);
        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int arg0) {}

            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            public void onPageSelected(int arg0) {
                indicatorView.setCurrentPage(arg0 % totalSize);
            }
        });
    }

    public void startAutoSctoll(int interval){
        bannerViewPager.setInterval(interval);
        bannerViewPager.startAutoScroll();
        int times = Integer.MAX_VALUE / totalSize;
        bannerViewPager.setCurrentItem(times / 2 * totalSize);
    }

    public void destory(){
        if(bannerViewPager != null){
            bannerViewPager.release();
        }
    }
}
