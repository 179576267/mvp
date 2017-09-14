package com.wzf.mvpdemo.ui.activity.widget;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-08-09 10:07
 */

public class ScrollIncludeListActivity extends BaseActivity {
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.scrollView)
    MyScrollView scrollView;
    private String[] strs = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};
    private boolean intercept = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        ButterKnife.bind(this);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ScrollIncludeListActivity.this, SlideMenuActivity.class));
            }
        });
        /**
         * 滑动冲突解决方法2从子控件出发
         */
//        lv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_MOVE:
//                        v.getParent().requestDisallowInterceptTouchEvent(true);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        v.getParent().requestDisallowInterceptTouchEvent(false);
//                        break;
//                }
//                return false;
//            }
//        });

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    intercept = false;
                    scrollView.myRequestDisallowInterceptTouchEvent(true);
                    Log.d("ListView", "##### 滚动到顶部 #####");
                } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    Log.d("ListView", "##### 滚动到底部 ######");
                    intercept = false;
                    scrollView.myRequestDisallowInterceptTouchEvent(true);
                } else {
                    Log.d("ListView", "##### 滚动到中间 ######");
                    intercept = true;
                    scrollView.myRequestDisallowInterceptTouchEvent(false);
                }
            }
        });
    }
}
