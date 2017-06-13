package com.wzf.mvpdemo.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.utils.ScreenUtils;
import com.wzf.mvpdemo.utils.imageloader.ImageLoader;

import java.util.List;

/**
 * zhenfei
 * 看点首页banner适配器
 */
public abstract class AdImagePagerAdapter extends RecyclingPagerAdapter {
	private Context context;
	private List<String> imageIdList;
	public AdImagePagerAdapter(Context context, List<String> imageIdList) {
		this.context = context;
		this.imageIdList = imageIdList;

	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public View getView(final int position, View view, ViewGroup container) {
		final int realPosition = position % imageIdList.size();
		ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = LayoutInflater.from(context).inflate(R.layout.news_ad_item, null);
			holder.imageView = (ImageView) view.findViewById(R.id.ad_item_iv);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		String imageUrl = imageIdList.get(realPosition);
		ImageLoader.build(context).bindBitmap(imageUrl, holder.imageView, ScreenUtils.getScreenWidth(MyApplication.getAppInstance()),
				ScreenUtils.dip2px(MyApplication.getAppInstance(),200));
		holder.imageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pageClick(realPosition);
			}
		});
		return view;
	}

	private static class ViewHolder {
		ImageView imageView;
	}

	public abstract void pageClick(int position);


}
