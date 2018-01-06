package com.wzf.mvpdemo.ui.activity.banner;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;


/**
 * Created by zhenfei.wang on
 * 23/12/2015.
 * 通用的viewpage适配器
 */

public class CommonPageAdapter extends PagerAdapter {
	private List<View> views;

	public CommonPageAdapter(List<View> views) {
		this.views = views;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		// 将指定的view从viewPager中移除
		((ViewPager) container).removeView(views.get(position % views.size()));
	}

	@Override
	public int getCount() {
		return views.size()== 1? 1 : Integer.MAX_VALUE;
	}

	public View getItemAtPosition(int position) {
		return views.get(position % views.size());
	}

	@Override
	public Object instantiateItem(View container, int position) {
		// 将view添加到viewPager中
		((ViewPager) container).addView(views.get(position % views.size()));
		return views.get(position % views.size());
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
