package com.wzf.mvpdemo.http.model;


import android.content.Intent;


import com.wzf.mvpdemo.MyApplication;
import com.wzf.mvpdemo.http.model.response.LogInDto;
import com.wzf.mvpdemo.utils.PreferencesHelper;

import java.util.Set;


/**
 * Created by YOUNG on 2017-03-17.
 */

public class UserInfo {
    public static final String ROLE_SPECIALIST = "1";
    public static final String ROLE_CASHIER = "2";
    public static final String ROLE_BOSS = "3";

    private String avatar = "avatar";
    private static UserInfo userInfo;
    private final PreferencesHelper mHelper;
    private String mobile = "mobile";
    private String name = "name";
    private String role = "role";
    private String shopIcon = "shopIcon";
    private String shopId = "shopId";
    private String shopName = "shopName";
    private String token = "token";
    private String uid = "uid";
    private String workNum = "workNum";
    private String payPass = "payPass";
    private String psw = "psw";

    private UserInfo() {
        mHelper = new PreferencesHelper(MyApplication.getAppInstance().getApplicationContext(), PreferencesHelper.TB_USER);
    }

    public String getAvatar() {
        return mHelper.getValue(avatar) == null ? "" : mHelper.getValue(avatar);
    }

    public void setAvatar(String avatar) {
        mHelper.setValue(this.avatar, avatar);
    }

    public String getPayPass() {
        return mHelper.getValue(payPass) == null ? "" : mHelper.getValue(payPass);
    }

    public void setPayPass(String payPass) {
        mHelper.setValue(this.payPass, payPass);
    }

    public String getMobile() {
        return mHelper.getValue(mobile) == null ? "" : mHelper.getValue(mobile);
    }

    public void setMobile(String mobile) {
        mHelper.setValue(this.mobile, mobile);
    }

    public String getName() {
        return mHelper.getValue(name) == null ? "" : mHelper.getValue(name);
    }

    public void setName(String name) {
        mHelper.setValue(this.name, name);
    }

    public String getRole() {
        return mHelper.getValue(role) == null ? "" : mHelper.getValue(role);
    }

    public void setRole(String role) {
        mHelper.setValue(this.role, role);
    }

    public String getShopIcon() {
        return mHelper.getValue(shopIcon) == null ? "" : mHelper.getValue(shopIcon);
    }

    public void setShopIcon(String shopIcon) {
        mHelper.setValue(this.shopIcon, shopIcon);
    }

    public String getShopId() {
        return mHelper.getValue(shopId) == null ? "" : mHelper.getValue(shopId);
    }

    public void setShopId(String shopId) {
        mHelper.setValue(this.shopId, shopId);
    }

    public String getShopName() {
        return mHelper.getValue(shopName) == null ? "" : mHelper.getValue(shopName);
    }

    public void setShopName(String shopName) {
        mHelper.setValue(this.shopName, shopName);
    }

    public String getToken() {
        return mHelper.getValue(token) == null ? "" : mHelper.getValue(token);
    }

    public void setToken(String token) {
        mHelper.setValue(this.token, token);
    }

    public String getUid() {
        return mHelper.getValue(uid) == null ? "" : mHelper.getValue(uid);
    }

    public void setUid(String uid) {
        mHelper.setValue(this.uid, uid);
    }

    public String getWorkNum() {
        return mHelper.getValue(workNum) == null ? "" : mHelper.getValue(workNum);
    }

    public void setWorkNum(String workNum) {
        mHelper.setValue(this.workNum, workNum);
    }

    public String getPsw() {
        return mHelper.getValue(psw) == null ? "" : mHelper.getValue(psw);
    }

    public void setPsw(String psw) {
        mHelper.setValue(this.psw, psw);
    }

    public static UserInfo getInstance() {
        if (userInfo == null) {
            synchronized (UserInfo.class) {
                if (userInfo == null) {
                    userInfo = new UserInfo();
                }
            }
        }
        return userInfo;
    }



    public void setLoginUserInfo(LogInDto logInDto){
        if(logInDto != null){
            setAvatar(logInDto.getAvatar());
            setMobile(logInDto.getMobile());
            setName(logInDto.getName());
            setRole(logInDto.getRole());
            setShopIcon(logInDto.getShopIcon());
            setShopId(logInDto.getShopId());
            setShopName(logInDto.getShopName());
            setToken(logInDto.getToken());
            setUid(logInDto.getUid());
            setWorkNum(logInDto.getWorkNum());
            setPsw(logInDto.getPsw());
            setPayPass(logInDto.getPayPass());
//            JPushInterface.setAlias(OrderSystemApplication.getAppInstance(), getUid(), new TagAliasCallback() {
//                @Override
//                public void gotResult(int i, String s, Set<String> set) {
//                    DebugLog.toast("设置别名："+ i + s);
//                }
//            });  // 别名推送
        }
    }
}
