package com.wzf.mvpdemo.http.model.request;

/**
 * 提现用的账户对象
 * Created by bean on 2017/3/16.
 */
public class ShopBindAccountRequestDto extends PostParam{

    private long id;

    private long shopId;

    /**
     * 类型 1.支付宝 2.微信 3.银行卡
     */
    private int type;

    /**
     * 提现账户(转账的账户)
     */
    private String extractAccount;

    /**
     * 客户端显示的账户
     */
    private String showAccount;

    /**
     * 账户昵称
     */
    private String accountName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExtractAccount() {
        return extractAccount;
    }

    public void setExtractAccount(String extractAccount) {
        this.extractAccount = extractAccount;
    }

    public String getShowAccount() {
        return showAccount;
    }

    public void setShowAccount(String showAccount) {
        this.showAccount = showAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

}
