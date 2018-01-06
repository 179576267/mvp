package com.wzf.mvpdemo.ui.activity.canvas;

import com.wzf.mvpdemo.ui.activity.banner.BannerActivity;
import com.wzf.mvpdemo.ui.activity.listslide.ListSlideActivity;
import com.wzf.mvpdemo.ui.activity.waterwave.MyWaterWaveActivity;
import com.wzf.mvpdemo.ui.activity.widget.ScrollIncludeListActivity;
import com.wzf.mvpdemo.ui.base.BaseMenuActivity;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-11-30 16:21
 */

public class CanvasMenuActivity extends BaseMenuActivity {
    @Override
    public void init() {
        addChild("画板初级，画时钟", CanvasClockActivity.class);
        addChild("投影图片和刮刮卡", ScratchCardActivity.class);
        addChild("图片编辑", ImageEditActivity.class);
        addChild("水波纹", MyWaterWaveActivity.class);
        addChild("滑动控件包含列表", ScrollIncludeListActivity.class);
        addChild("自定义轮播图", BannerActivity.class);
        addChild("左滑删除", ListSlideActivity.class);
    }
}
