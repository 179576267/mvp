package com.wzf.mvpdemo.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.wzf.mvpdemo.ui.dialog.NetRequestWaitDialog;
import com.wzf.mvpdemo.utils.SoftInputUtil;

import butterknife.ButterKnife;

/**
 * Created by zhenfei.wang on 2016/8/8.
 */
public class BaseActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private NetRequestWaitDialog dialog;
    protected boolean hasShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);// 不锁屏
//        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        hasShow = true;
//        ActivityCollector.setCurrentActivity(this);
    }

    /**
     * 弹出吐丝
     * @param text
     */
    Toast toast;
    public void showToast(String text){
        if(toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 弹出吐丝
     * @param s
     */
    public void showDialog(String s){
        if (dialog == null) {
            dialog = new NetRequestWaitDialog(this, s);
        }
        dialog.setMesssage(s);
        if (dialog.isShowing()) {
            return;
        }
        if(!this.isFinishing()){
            dialog.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dissMissDialog();
        if(toast != null){
            toast.cancel();
        }
        dialog = null;
    }
    public void dissMissDialog(){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    @Override
    public void finish() {
        SoftInputUtil.closeSoftInput(this);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
