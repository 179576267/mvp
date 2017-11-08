package com.wzf.mvpdemo.ui.activity.listslide;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.ui.adapter.CommonAdapter;
import com.wzf.mvpdemo.ui.adapter.ViewHolder;
import com.wzf.mvpdemo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-09-26 09:41
 */

public class ListSlideActivity extends BaseActivity {
    private ListView lv;
    private  List<String> list;
    private CommonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_slide);
        lv = (ListView) findViewById(R.id.lv);
        String[] data = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        list = new ArrayList<String>();
        for(int i = 0 ; i < data.length ; i++){
            list.add(data[i]);
        }
        lv.setAdapter(adapter = new CommonAdapter<String>(list, this, R.layout.item_list_slide) {
            @Override
            public void convert(ViewHolder viewHolder, final String item) {
                TextView tvContent = viewHolder.getView(R.id.view_content);
                TextView tvMenu = viewHolder.getView(R.id.view_menu);
                final ItemSlideView slideView = (ItemSlideView) viewHolder.getConvertView();
                slideView.setOnStatusChangeListener(new MyOnStatusChangeListener());
                tvContent.setText(item);
                tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast(item);
                    }
                });

                tvMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        slideView.closeMenu();
                        showToast("delete: " + item);
                        list.remove(item);
                        notifyDataSetChanged();
                    }
                });
            }

            private ItemSlideView slideView;
            class MyOnStatusChangeListener implements ItemSlideView.OnStatusChangeListener{
                @Override
                public void onOpen(ItemSlideView view) {
                    slideView = view;
                }

                @Override
                public void onDown(ItemSlideView view) {
                    if(slideView != null && view != slideView){
                        slideView.closeMenu();
                    }
                }

                @Override
                public void onClose(ItemSlideView view) {
                    if(view == slideView){
                        slideView = null;
                    }
                }
            }
        });
    }
}
