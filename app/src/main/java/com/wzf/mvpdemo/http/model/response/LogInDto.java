package com.wzf.mvpdemo.http.model.response;

/**
 * Created by YOUNG on 2017-03-17.
 */

public class LogInDto {

    /**
     * avatar :
     * createTime : 1489634139000
     * isDel : 0
     * mobile : 18221003056
     * name : 张三丰
     * role : 1
     * salt : bb33ecaf69cf45fda637498b18591877
     * shopIcon : xxx.png
     * shopId : 1
     * shopName : 逗趣网络
     * token : app-token:1:0de97626-498a-43aa-9cce-8603bc725ade
     * uid : 1
     * workNum : 002
     */

    private String avatar;
    private String createTime;
    private String isDel;
    private String mobile;
    private String name;
    private String role;
    private String salt;
    private String shopIcon;
    private String shopId;
    private String shopName;
    private String token;
    private String uid;
    private String workNum;
    private String payPass;
    private String psw;

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPayPass() {
        return payPass;
    }

    public void setPayPass(String payPass) {
        this.payPass = payPass;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    @Override
    public String toString() {
        return "LogInDto{" +
                "avatar='" + avatar + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isDel='" + isDel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", salt='" + salt + '\'' +
                ", shopIcon='" + shopIcon + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                ", workNum='" + workNum + '\'' +
                ", payPass='" + payPass + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
