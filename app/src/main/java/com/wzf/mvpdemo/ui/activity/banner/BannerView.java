package com.wzf.mvpdemo.ui.activity.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
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

import java.lang.reflect.Field;
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
    private LinearLayout llBottom;
    private Context mContext;
    private int oldPosition;
    private boolean isDragging = false;
    private int interval = 3000;
    private OnPageClickListener pageClickListener;

    private boolean noTitleMode;

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
        if(points.size() > 1){
            postDelayed(runnable, interval);
        }

    }

    public void setNoTitleMode(boolean noTitleMode){
        this.noTitleMode = noTitleMode;
    }

    private void init() {
//        View root = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_info, null, false);
//        vp = (ViewPager) root.findViewById(R.id.vp);
        //        tvTitle = (TextView) root.findViewById(R.id.tv_title);
//        llContainer = (LinearLayout) root.findViewById(R.id.ll_container);
//        addView(root);
        RelativeLayout mRelativeLayout = new RelativeLayout(mContext);
        vp = new ViewPager(mContext);
        tvTitle = new TextView(mContext);
        tvTitle.setTextSize(12);
        tvTitle.setTextColor(0XFFFFFFFF);
        tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        llContainer = new LinearLayout(mContext);
        llContainer.setGravity(Gravity.CENTER_HORIZONTAL);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        lp1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        mRelativeLayout.addView(vp, lp1);


        llBottom = new LinearLayout(mContext);
        llBottom.setOrientation(LinearLayout.VERTICAL);
        llBottom.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        llBottom.setPadding(5,5,5,8);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        llBottom.addView(tvTitle, params);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.setMargins(0,5,0,0);
        llBottom.addView(llContainer, params);

        lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mRelativeLayout.addView(llBottom, lp1);


        addView(mRelativeLayout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
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
        final boolean isTwo = infos.size() == 2;
        List<BannerInfo> infoList = new ArrayList<>();
        infoList.addAll(infos);
        if(isTwo){
            infoList.addAll(infos);
        }
        for(int i = 0; i < infoList.size(); i++){
            BannerInfo info = infoList.get(i);
            ImageView im = getImageView(isTwo, i, info);
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
        int centerPosition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % infoList.size();
        vp.setCurrentItem(centerPosition);

        if(noTitleMode){
            llBottom.setBackgroundColor(0x00000000);
            tvTitle.setVisibility(GONE);
        }else {
            llBottom.setBackgroundColor(0x50000000);
            tvTitle.setVisibility(VISIBLE);
        }
    }

    @NonNull
    private ImageView getImageView(final boolean isTwo, int i, BannerInfo info) {
        ImageView im= new ImageView(mContext);
        im.setScaleType(ImageView.ScaleType.CENTER_CROP);
        im.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ImageLoader.getInstance().loadImage(info.image, im);
        im.setOnTouchListener(new OnTouchListener() {
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
        im.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageClickListener != null){
                    pageClickListener.onPageClick(isTwo?position % 2 : position);
                }
            }
        });
        if(isTwo){
            if(i < infos.size()){
                addIndexBar(i, info);
            }
        }else {
            addIndexBar(i, info);
        }
        return im;
    }

    private void addIndexBar(int i, BannerInfo info) {
        TextView textView = new TextView(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dip2px(mContext, 8),
                ScreenUtils.dip2px(mContext, 8));
        if(i != 0){
            params.setMargins(10, 0, 0, 0);
        }else{
            tvTitle.setText(info.title);
        }
        textView.setLayoutParams(params);
        textView.setEnabled(false);
        textView.setBackgroundResource(R.drawable.selector_banner_point);
        points.add(textView);
        llContainer.addView(textView);
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
