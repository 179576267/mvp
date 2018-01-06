package com.wzf.mvpdemo.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wzf.mvpdemo.ui.activity.StartActivity;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-11-30 16:06
 */

public class BaseMenuActivity extends BaseActivity {
    protected LinearLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView  = new ScrollView(this);
        scrollView.setPadding(0, 50, 0, 0);
        scrollView.setClipToPadding(true);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(root);
        setContentView(scrollView);
        init();
    }

    public void init() {
    }


    protected void addChild(String text, final Class t) {
        Button button = new Button(this);
        button.setText(text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseMenuActivity.this, t));
            }
        });
        root.addView(button);

    }


    protected void addChild(String text, View.OnClickListener onClickListener) {
        Button button = new Button(this);
        button.setText(text);
        button.setOnClickListener(onClickListener);
        root.addView(button);

    }
}
