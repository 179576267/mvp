package com.wzf.mvpdemo.ui.activity.widget;

import android.os.Bundle;
import android.widget.ListView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.adapter.CommonAdapter;
import com.wzf.mvpdemo.ui.adapter.ViewHolder;
import com.wzf.mvpdemo.ui.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-08-10 09:18
 */

public class SlideMenuActivity extends BaseActivity {
    @Bind(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        ButterKnife.bind(this);
        lv.setAdapter(new CommonAdapter<String>(Arrays.asList("", "", "", "", ""), this, R.layout.item_list_qq) {
            @Override
            public void convert(ViewHolder viewHolder, String item) {

            }
        });

    }
}
