package com.wzf.mvpdemo.http.model.request;

/**
 * Created by Administrator on 2017/3/16.
 */
public class ExtractApplyRequestDto extends PostParam{

    /**
     * 提现方式 E_PayType枚举值对应
     */
    private int type;

    /**
     * 提现金额
     */
    private int money;

    /**
     * 账户ID
     */
    private long accountId;

    /**
     * 支付密码
     */
    private String payPass;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getPayPass() {
        return payPass;
    }

    public void setPayPass(String payPass) {
        this.payPass = payPass;
    }
}
