package com.wzf.mvpdemo.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.R;
import com.wzf.mvpdemo.http.model.UserInfo;
import com.wzf.mvpdemo.ui.base.BaseActivity;
import com.wzf.mvpdemo.utils.REGX;
import com.wzf.mvpdemo.utils.SoftInputUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class LogInActivity extends BaseActivity implements LoginContract.View{
    private LoginContract.Presenter presenter;
    @Bind(R.id.rl_layout_log)
    RelativeLayout rlLayoutLog;
    @Bind(R.id.et_account_num)
    EditText etAccountNum;
    @Bind(R.id.et_account_psw)
    EditText etAccountPsw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_log);
        ButterKnife.bind(this);
        initView();
        presenter = new LoginPresenter(this,this);
    }

    private void initView() {
        etAccountNum.setFilters(REGX.getFilters(REGX.REGX_MOBILE_INPUT));
        etAccountNum.setText(UserInfo.getInstance().getMobile());
        etAccountNum.setSelection(UserInfo.getInstance().getMobile().length());
        etAccountPsw.setText(UserInfo.getInstance().getPsw());
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @OnClick({R.id.btn_log_in, R.id.rl_layout_log})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log_in:
                String accountNum = etAccountNum.getText().toString().trim();
                String psw = etAccountPsw.getText().toString().trim();
                presenter.login(accountNum, psw);
                break;
            case R.id.rl_layout_log:
                SoftInputUtil.hideSoftInput(etAccountNum);
                break;
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(MyApplication.getAppInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}
