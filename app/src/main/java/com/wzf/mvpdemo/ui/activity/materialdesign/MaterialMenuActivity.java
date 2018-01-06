package com.wzf.mvpdemo.ui.activity.materialdesign;

import com.wzf.mvpdemo.ui.base.BaseMenuActivity;

/**
 * @author Administrator
 */
public class MaterialMenuActivity extends BaseMenuActivity {

    @Override
    public void init() {
        addChild("Toolbar", ToolbarActivity.class);
        addChild("CoordinatorLayout", CoordinatorLayoutScrollActivity.class);
        addChild("CollapsingToolBarLayout", CollapsingToolBarLayoutActivity.class);
    }
}
