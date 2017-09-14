package com.wzf.mvpdemo.ui.activity.widget;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Scroller;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.utils.DebugLog;
import com.yixia.tools.ScreenUtils;

public class MyViewPager extends ViewGroup {
	private int mTouchSlop;
	private float downX;
	private float moveX;
	private float lastMoveX;
	private int leftBound;
	private int rightBound;
	private String TAG = "ricky";
	private Scroller mScroller;
	private int screenWidth = ScreenUtils.getScreenWidth(MyApplication.getAppInstance());

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViewPager(context);
	}

	private void initViewPager(Context context) {
//        mScroller = new Scroller(context, sInterpolator);
		final ViewConfiguration configuration = ViewConfiguration.get(context);
		//系统允许的滑动的最小距离
		mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
		mScroller = new Scroller(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int size = getChildCount();
		for (int i = 0; i < size; ++i) {
			final View child = getChildAt(i);
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if(changed){
			int size = getChildCount();
			for (int i = 0; i < size; ++i) {
				final View child = getChildAt(i);
				child.layout(i*child.getMeasuredWidth(), 0, (i+1)*child.getMeasuredWidth(), child.getMeasuredHeight());
				child.setClickable(true);
			}
		}
		//左边界
		leftBound = getChildAt(0).getLeft();
		//右边界
		rightBound = getChildAt(getChildCount()-1).getRight();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("ricky", "onIntercept");
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = ev.getRawX();
				lastMoveX = downX;
				requestParentDisallowInterceptTouchEvent(true);
				break;
			case MotionEvent.ACTION_MOVE:
				moveX = ev.getRawX();
				float xDiff = Math.abs(moveX - downX);
				lastMoveX = moveX;
				Log.i(TAG , "moveX:"+moveX+", xDiff:"+xDiff+", mTouchSlop:"+mTouchSlop);
				if (xDiff > mTouchSlop){
					return true;
				}
				break;

			default:
				break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
		final ViewParent parent = getParent();
		if (parent != null) {
			parent.requestDisallowInterceptTouchEvent(disallowIntercept);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//处理滑动的逻辑
		/**
		 * 如何有滑动的效果？
		 * View.scrollTo(x,y);
		 * 		让View相对于它初始的位置滚动一段距离。
		 * View.scrollBy(x,y);
		 * 		让View相对于它现在的位置滚动一段距离。
		 * 注意：上面两种方法都是滑动View里面的内容，即里面的所有子控件。
		 *
		 */
		switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				moveX = event.getRawX();
				int scrollDx = (int) (lastMoveX - moveX);
				Log.i(TAG , "~~~~moveX:"+moveX+", scrollDx:"+scrollDx+", lastMoveX:"+lastMoveX+", leftBound"+leftBound);
				//左边界判断，如果不断拖拽到达了左边界则拖拽就失效了
				if (getScrollX()+scrollDx < leftBound) {
					scrollTo(leftBound, 0);
					return true;
				} else if (getScrollX()+scrollDx+getWidth() > rightBound) {
					scrollTo(rightBound - getWidth(), 0);
					return true;
				}
				scrollBy(scrollDx, 0);
				lastMoveX = moveX;
				break;
			case MotionEvent.ACTION_UP:
				float upX = event.getX();
				actionUpHandel(upX);
				break;

			default:
				break;
		}
		return super.onTouchEvent(event);
	}

	private void actionUpHandel(float upX) {
//		if(getScrollX() <= getWidth() / 2){
//			scrollTo(0 ,0);
//		}else if(getWidth() / 2 < getScrollX()&& getScrollX() <= getWidth() * 3 / 2){
//			scrollTo(getWidth(),0);
//		}else if(getScrollX() > getWidth() * 3 / 2){
//			scrollTo(getWidth() * 2, 0 );
//		}
		double d = (getScrollX() + 0.5d * screenWidth)/ screenWidth;
		int count = (int) Math.floor(d);
		DebugLog.i("getScrollX():" + getScrollX() + ", upX:" + upX);
		mScroller.startScroll(getScrollX(), 0, count * screenWidth - getScrollX(), 0);
		invalidate();

	}

	@Override
	public void computeScroll() {
		if(mScroller.computeScrollOffset()){
//			DebugLog.i("CurrX:" + mScroller.getCurrX() + " ,final X :" + mScroller.getFinalX());
			this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			//必须调用该方法，否则不一定能看到滚动效果
			postInvalidate();
		}
	}
}
