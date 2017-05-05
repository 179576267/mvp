package com.wzf.mvpdemo.ui.dialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.utils.ScreenUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangzhenfei on 2016/11/2.
 */
public class NetRequestWaitDialog extends Dialog{
    @Bind(R.id.tv)
    TextView tv;
    private Context mContext;
    private String messsage;

    public NetRequestWaitDialog(Context context, String content) {
        super(context);
        mContext = context;
        this.messsage = content;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
        if(tv != null){
            tv.setText(messsage);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        //设置无标题  需在setContentView之前
        window.requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_net_request);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int)(ScreenUtils.getScreenWidth(mContext) * 3.0 / 4);
        ButterKnife.bind(this);
        setCancelable(false);
        setMessage();
    }

    private void setMessage() {
        if(tv != null){
            tv.setText(messsage);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mContext = null;
    }
}
