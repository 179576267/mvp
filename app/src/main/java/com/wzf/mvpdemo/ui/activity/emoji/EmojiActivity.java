package com.wzf.mvpdemo.ui.activity.emoji;

import android.os.Bundle;
import android.text.Spannable;
import android.widget.TextView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.base.BaseActivity;
import com.wzf.mvpdemo.utils.EmojiUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-08 16:16
 */

public class EmojiActivity extends BaseActivity {
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        ButterKnife.bind(this);
        String text = "123放假搜救[:s]接[8-|][(u)]3few [(S)]";
        Spannable span = EmojiUtils.getSmiledText(this, text);
        // 设置内容
        tv.setText(span, TextView.BufferType.SPANNABLE);
    }
}
