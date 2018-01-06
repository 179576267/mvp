package com.wzf.mvpdemo.ui.activity.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.utils.ScreenUtils;
import com.wzf.mvpdemo.utils.imageloaderbyqueue.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-14 09:39
 */

public class BannerView extends RelativeLayout {
    private List<BannerInfo> infos;
    private List<TextView> points = new ArrayList<>();
    private ViewPager vp;
    private TextView tvTitle;
    private LinearLayout llContainer;
    private Context mContext;
    private int oldPosition;
    private boolean isDragging = false;
    private int interval = 3000;
    private OnPageClickListener pageClickListener;

    Runnable runnable=new Runnable(){
        @Override
        public void run() {
            vp.setCurrentItem(vp.getCurrentItem() + 1);
            postDelayed(this, interval);
        }
    };

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
//        postDelayed(runnable, interval);
    }


    /**
     * 当布局文件加载完成的时候调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(runnable, interval);
    }

    private void init() {
        View root = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_info, null, false);
        vp = (ViewPager) root.findViewById(R.id.vp);
        tvTitle = (TextView) root.findViewById(R.id.tv_title);
        llContainer = (LinearLayout) root.findViewById(R.id.ll_container);
        addView(root);
    }

    public void setBannerInfos(List<BannerInfo> list, OnPageClickListener listener){
        if(list == null || list.size() == 0){
            setVisibility(GONE);
            return;
        }
        pageClickListener = listener;

        infos = list;
        initData();
    }

    private void initData() {
        List<View> views = new ArrayList<>();
        for(int i = 0; i < infos.size(); i++){
            BannerInfo info = infos.get(i);
            ImageView im= new ImageView(mContext);
            im.setScaleType(ImageView.ScaleType.CENTER_CROP);
            im.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageLoader.getInstance().loadImage(info.image, im);
            im.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                           removeCallbacks(runnable);
                            break;
                        case MotionEvent.ACTION_UP:
                            removeCallbacks(runnable);
                            postDelayed(runnable, interval);
                            break;
                    }
                    return false;
                }
            });
            final int position = i;
            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pageClickListener != null){
                        pageClickListener.onPageClick(position);
                    }
                }
            });
            TextView textView = new TextView(mContext);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dip2px(mContext, 8),
                    ScreenUtils.dip2px(mContext, 8));
            if(i != 0){
                params.setMargins(10, 0, 0, 0);
            }
            textView.setLayoutParams(params);
            textView.setEnabled(false);
            textView.setBackgroundResource(R.drawable.selector_banner_point);
            points.add(textView);
            llContainer.addView(textView);
            views.add(im);
        }
        CommonPageAdapter pageAdapter = new CommonPageAdapter(views);
        vp.setAdapter(pageAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = position % infos.size();
                tvTitle.setText(infos.get(realPosition).title);
                points.get(oldPosition).setEnabled(false);
                points.get(realPosition).setEnabled(true);
                oldPosition = realPosition;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_DRAGGING){
                    isDragging = true;
                    removeCallbacks(runnable);
                }else if(state == ViewPager.SCROLL_STATE_IDLE){
                    if(isDragging){
                        removeCallbacks(runnable);
                        postDelayed(runnable, interval);
                        isDragging = false;
                    }
                }
            }
        });
        int centerPosition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % infos.size();
        vp.setCurrentItem(centerPosition);
    }

    @Override
    protected void onDetachedFromWindow() {
        removeCallbacks(runnable);
        super.onDetachedFromWindow();
    }

    public static class BannerInfo{
        public String title;
        public String image;

        public BannerInfo() {
        }

        public BannerInfo(String title, String image) {
            this.title = title;
            this.image = image;
        }
    }

    public interface OnPageClickListener{
        void onPageClick(int position);
    }


}
